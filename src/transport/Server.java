package transport;

import dao.BoardGameDao;
import dao.UserDao;
import model.BoardGameModel;
import model.User;
import model.search.BoardGameSearched;
import util.Database;
import model.GameLog;
import util.TransportThings;
import util.myexception.AccountAlreadyExistException;
import util.myexception.AccountNotExistException;
import util.myexception.NoSearchResultException;
import util.myexception.WrongPassWdException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ServerSocket service;

    public void start() {
        try {
            String DEFAULT_IP = "127.0.0.1";
            InetAddress address = InetAddress.getByName(DEFAULT_IP);
            Socket connect = null;
            int MAX_CLIENT_CNT = 5;
            ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENT_CNT);
            int DEFAULT_PORT = 1100;
            service = new ServerSocket(DEFAULT_PORT, MAX_CLIENT_CNT, address);
            while (true) {
                connect = service.accept();
                System.out.println(connect.toString());
                //创建一个任务,放入线程池等待运行
                ServiceTask serviceTask = new ServiceTask(connect);
                pool.execute(serviceTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.start();
    }
}

class ServiceTask implements Runnable {
    private final ObjectInputStream obj_is;
    private final ObjectOutputStream obj_os;
    private final UserDao userDao;
    private BoardGameDao boardGameDao;
    private User currentUser;//default

    ServiceTask(Socket socket) throws IOException {
        Database database = new Database();
        userDao = new UserDao(database);
        boardGameDao = new BoardGameDao(database);
        obj_is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        obj_os = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {//要执行的server体在这里面
        boolean exitFlag = false;
        while (true) {
            TransportThings tt = null;
            try {
                tt = (TransportThings) readObj_throw_exception();
            } catch (Exception e) {
                System.out.println("退出");
                break;
            }
            parseTransportThings(tt, exitFlag);
            if (exitFlag) break;
        }
    }

    private boolean writeObj(Object obj) {
        boolean flag = false;
        try {
            obj_os.writeObject(obj);
            obj_os.flush();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private Object readObj() {
        try {
            return obj_is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object readObj_throw_exception() throws IOException, ClassNotFoundException {
        return obj_is.readObject();
    }

    /**
     * 解析接收的TransportThings对象
     *
     * @param tt       TransportThings
     * @param exitFlag 用于传出结束标志
     */
    private void parseTransportThings(TransportThings tt, boolean exitFlag) {
        TransportThings tt_ret = new TransportThings();
        switch (tt.getQuery()) {
            case "login": // fixme:非原子操作,需要加数据库锁，没有进行数据库修改好像问题不大
                try {
                    User user = userDao.search(tt.getUser().getUserName(), tt.getUser().getPassWord());
                    tt_ret.setUser(user);
                    tt_ret.setState(0x01);
                    currentUser = user; // 线程用户
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    tt_ret.setInfo("failed to access database");
                } catch (AccountNotExistException e) {
                    e.printStackTrace();
                    tt_ret.setInfo("Account do not exist");
                } catch (WrongPassWdException e) {
                    e.printStackTrace();
                    tt_ret.setInfo("wrong password");
                }
                break;
            case "signup": // fixme:非原子操作,需要加数据库锁,没有进行数据库修改好像问题不大
                User user = tt.getUser();
                try {
                    User user1 = userDao.search(user.getUserName(), user.getPassWord());
                    if (user1 != null)
                        throw new AccountAlreadyExistException();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    tt_ret.setInfo("failed to access database");
                } catch (AccountNotExistException e) {
                    if (userDao.insert(user.getUserName(), user.getPassWord())) {
                        tt_ret.setInfo("signup success");
                        tt_ret.setState(0x01);
                        try {
                            tt_ret.setUser(userDao.search(user.getUserName(), user.getPassWord()));
                        } catch (SQLException | AccountNotExistException | WrongPassWdException ee) {
                            ee.printStackTrace();
                        }
                    } else {
                        tt_ret.setInfo("failed to access database");
                    }
                } catch (WrongPassWdException | AccountAlreadyExistException e) {
                    tt_ret.setInfo("Account already exist");
                }
                break;
            case "search":
                String searchstr = tt.getInfo();
                BoardGameSearched boardGameSearched = boardGameDao.search(searchstr);
                try {
                    ArrayList<BoardGameModel> boardGameModels = boardGameDao.Browser();
                    tt_ret.setBoardGameModels(boardGameModels);
                    tt_ret.setState(0x01);
                } catch (NoSearchResultException e) {
                    tt_ret.setInfo("noResult");
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    tt_ret.setInfo("CANNOT ACCESS DATABASE");
                    throwables.printStackTrace();
                }
                break;
            case "top10":
                try {
                    ArrayList<BoardGameModel> boardGameModels = boardGameDao.Browser();
                    tt_ret.setBoardGameModels(boardGameModels);
                    tt_ret.setState(0x01);
                } catch (NoSearchResultException e) {
                    tt_ret.setInfo("noResult");
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    tt_ret.setInfo("CANNOT ACCESS DATABASE");
                    throwables.printStackTrace();
                }
                break;
            case "gamelog":
                //TODO: gamelog部分没写完
                System.out.println("gamelog recv");
                GameLog gameLog = tt.getGameLog();
                User theUser = tt.getUser();
                try {
                    boardGameDao.logGame(gameLog, theUser);
                    tt_ret.setState(0x01);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    tt_ret.setInfo("cannot access database");
                }
                System.out.println(tt_ret.getState());
                break;
            default:
                break;
        }
        writeObj(tt_ret);
    }
}
