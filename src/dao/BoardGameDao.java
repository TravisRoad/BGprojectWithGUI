package dao;

import model.BoardGameModel;
import model.User;
import model.boardgamefetched.BoardGameFetched;
import model.search.BoardGameSearched;
import util.Database;
import model.GameLog;
import util.JsonConvert;
import util.TransportThings;
import util.XMLtoJSON;
import util.httpRequest.MyGetRequest;
import util.myexception.NoSearchResultException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BoardGameDao {
    private Database database;
    //private BoardGameFetched boardGameFetched;

    public BoardGameDao() {

    }

    public BoardGameDao(Database database) {
        this.database = database;
        //boardGameFetched = new BoardGameFetched();
    }

    /**
     * 和API接口进行交互，得到指定id的桌游的信息
     *
     * @param bg_id boardgame id
     * @return BoardGameFetched
     */
    public BoardGameFetched fetchBoardGameInfo(int bg_id) {
        MyGetRequest myGetRequest = new MyGetRequest();
        String json = myGetRequest.request(bg_id);
        JsonConvert jsonConvert = new JsonConvert();
        return jsonConvert.convert2BoardGameFetched(json);
    }

    /**
     * 搜索一些桌游的信息，通过API获取信息
     *
     * @param thingstoSearch 搜索的字段
     * @return BoardGameSearched 存有返回的结果
     * @deprecated
     */
    public BoardGameSearched search(String thingstoSearch) {
        String xml = MyGetRequest.request(thingstoSearch);
        String json = XMLtoJSON.convert(xml);
        return JsonConvert.convert2BoardgameSearched(json);
    }

    /**
     * @param thingstoSearch 查询字段
     * @return 返回查询得到的所有桌游的列表
     * @throws NoSearchResultException 没有结果
     * @throws SQLException            SQL错误
     */
    public ArrayList<BoardGameModel> search0(String thingstoSearch) throws NoSearchResultException, SQLException {
        String sql = "SELECT * FROM boardgame WHERE name like ? limit 10;";
        ArrayList<BoardGameModel> boardGameList = new ArrayList<>();
        try (PreparedStatement ps = database.getConn().prepareStatement(sql)) {
            ps.setObject(1, "%" + thingstoSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bg_id = rs.getInt(1);
                String name = rs.getString(2);
                String intro = rs.getString(3);
                double rating = rs.getDouble(4);
                boardGameList.add(new BoardGameModel(bg_id, name, intro, rating));
            }
            if (boardGameList.isEmpty()) {
                throw new NoSearchResultException();
            }
        }
        return boardGameList;
    }

    /**
     * 查询top10
     *
     * @return
     */
    public ArrayList<BoardGameModel> Browser() throws SQLException, NoSearchResultException {
        String sql = "SELECT * FROM boardgame limit 10";
        ArrayList<BoardGameModel> boardGameList = new ArrayList<>();
        try (PreparedStatement ps = database.getConn().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bg_id = rs.getInt(1);
                String name = rs.getString(2);
                String intro = rs.getString(3);
                double rating = rs.getDouble(4);
                boardGameList.add(new BoardGameModel(bg_id, name, intro, rating));
            }
            if (boardGameList.isEmpty()) {
                throw new NoSearchResultException();
            }
        }
        return boardGameList;
    }

    /**
     * 记录游戏记录
     * 首先插入play_history中，再根据ph_id,将其与各个user联系
     *
     * @param gameLog gamelog类
     * @param user    发起者
     * @return true or false
     * @throws SQLException
     */
    public boolean logGame(GameLog gameLog, User user) throws SQLException {
        String sql0 = "INSERT INTO play_history (playdate,bg_id,username) VALUES (?,?,?);";
        String sql1 = "SELECT ph_id FROM play_history where ph_id in (SELECT max(ph_id) from play_history);";
        String sql2 = "INSERT INTO play (userName,ph_id) VALUES (?,?);";
        boolean ret = false;
        try (PreparedStatement ps = database.getConn().prepareStatement(sql0)) {
            ps.setObject(1, gameLog.getDate());
            ps.setObject(2, gameLog.getBg_id());
            ps.setObject(3, user.getUserName()); // 发起者姓名
            ret = ps.execute();
            ret = true;
        }
        int id = 0;
        try (PreparedStatement ps = database.getConn().prepareStatement(sql1)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }
        for (String name : gameLog.getUserNames()) {
            try (PreparedStatement ps = database.getConn().prepareStatement(sql2)) {
                ps.setObject(1, name);
                ps.setObject(2, id);
                ret = ps.execute();
                ret = true;
            }
        }
        return true;
    }

    /**
     * @param username 用户名
     * @return 搭载的包
     * @throws SQLException            MySql访问错误
     * @throws NoSearchResultException 无结果访问
     */
    public TransportThings RecentlyPlayed(String username) throws SQLException, NoSearchResultException {
        String sql = "SELECT bg_id, name, introduction, playdate, ph_id FROM play_history natural join boardgame WHERE username = ? order by playdate desc limit 10";
        ArrayList<GameLog> gameLogList = new ArrayList<>();
        ArrayList<BoardGameModel> boardGameModels = new ArrayList<>();
        TransportThings tt = new TransportThings();

        try (PreparedStatement ps = database.getConn().prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GameLog tmpLog = new GameLog();

                int bg_id = rs.getInt(1);
                String name = rs.getString(2);
                String intro = rs.getString(3);
                Date playdate = rs.getDate(4);
                int ph_id = rs.getInt(5);

                tmpLog.setBg_id(bg_id);
                tmpLog.setDate(playdate);
                ArrayList<String> usernames = UsersPlayedwith(ph_id);
                tmpLog.setUserNames(usernames);

                BoardGameModel boardGameModel = new BoardGameModel();
                boardGameModel.setBg_id(bg_id);
                boardGameModel.setName(name);
                boardGameModel.setIntroduction(intro);

                boardGameModels.add(boardGameModel);
                gameLogList.add(tmpLog);
            }
            if (gameLogList.isEmpty()) {
                throw new NoSearchResultException();
            }
        }
        tt.setBoardGameModels(boardGameModels);
        tt.setGameLogs(gameLogList);
        return tt;
    }

    private ArrayList<String> UsersPlayedwith(int ph_id) throws SQLException, NoSearchResultException {
        String sql = "Select userName from play where ph_id = ?";
        ArrayList<String> userNames = new ArrayList<>();
        try (PreparedStatement ps = database.getConn().prepareStatement(sql)) {
            ps.setObject(1, ph_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userNames.add(rs.getString(1));
            }
        }
        if (userNames.isEmpty()) {
            throw new NoSearchResultException();
        }
        return userNames;
    }


}