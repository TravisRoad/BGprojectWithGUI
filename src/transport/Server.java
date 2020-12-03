package transport;

import dao.BoardGameDao;
import dao.UserDao;
import model.BoardGameModel;
import model.User;
import model.search.BoardGameSearched;
import util.Database;
import model.GameLog;
import util.Time;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Travis
 * 用于服务端的程序，其中使用了线程池以处理并发的操作</br>
 * 虽然监听的是本地的连接请求，但是可以将程序放在服务器上运行，接收来自互联网的请求
 */
public class Server {
    ServerSocket service;

    /**
     * 用于开始服务器端的服务
     */
    public void start() {
        try {
            String DEFAULT_IP = "127.0.0.1"; // 监听本地，可以实现在远端运行
            InetAddress address = InetAddress.getByName(DEFAULT_IP);
            Socket connect = null;
            int MAX_CLIENT_CNT = 10; // 最大连接数
            ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENT_CNT);
            int DEFAULT_PORT = 1100;
            service = new ServerSocket(DEFAULT_PORT, MAX_CLIENT_CNT, address);
            while (true) {
                connect = service.accept();
                Time.println("有新连接接入" + connect.toString());
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

/**
 * 服务器端的线程池中的实现了Runnable接口的对象
 *
 * @implNote Runnable
 */
class ServiceTask implements Runnable {
    private final ObjectInputStream obj_is;
    private final ObjectOutputStream obj_os;
    private final UserDao userDao;
    private BoardGameDao boardGameDao;
    private User currentUser;//default

    /**
     * 构造方法
     *
     * @param socket 来自远端的socket
     * @throws IOException 如果远端关闭则会失去连接
     */
    ServiceTask(Socket socket) throws IOException {
        Database database = new Database();
        userDao = new UserDao(database);
        boardGameDao = new BoardGameDao(database);
        obj_is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        obj_os = new ObjectOutputStream(socket.getOutputStream());
    }

    /**
     * 要执行的server体在这里面,整体结构是：</br>
     * 接收TransportThings对象，解读其中的query字段，执行相应操作，写回回应的TransportThings对象</br>
     * 并且能够捕获到远端关闭的异常，如果关闭，则服务端会关闭该线程
     */
    @Override
    public void run() {
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

    /**
     * 向Socket对象的输出流写入传输对象
     *
     * @param obj 待传输的对象
     * @return 传输是否成功
     */
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

    /**
     * 从Socket对象的输入流读出传输的对象
     *
     * @return 传输对象
     */
    private Object readObj() {
        try {
            return obj_is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将异常抛出的特殊读出传输对象的方法
     *
     * @return 传输对象
     * @throws IOException            异常
     * @throws ClassNotFoundException 异常
     */
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
                try {
                    ArrayList<BoardGameModel> boardGameModels = boardGameDao.search0(searchstr);
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
                Time.println("gamelog recv");
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
            case "recent"://查询最近游玩
                Time.println(currentUser.getUserName() + " is getting recently played");
                try {
                    tt_ret = boardGameDao.RecentlyPlayed(tt.getUser().getUserName());
                    tt_ret.setState(0x01);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    tt_ret.setInfo("cannot access database");
                } catch (NoSearchResultException e) {
                    e.printStackTrace();
                    tt_ret.setInfo("NoSearchResultException");
                }
                break;
            case "changeNickName":
                Time.println(currentUser.getUserName() + " change nickName");
                try {
                    userDao.changeNickName(tt.getUser().getNickName(), (int) tt.getUser().getUserID());
                    tt_ret.setState(0x01);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    tt_ret.setInfo("cannot access database");
                } catch (NoSearchResultException e) {
                    e.printStackTrace();
                    tt_ret.setInfo("NoSearchResultException");
                }
            default:
                break;
        }
        writeObj(tt_ret);
    }
}
