package dao;

import model.BoardGameModel;
import model.User;
import model.boardgamefetched.BoardGameFetched;
import model.search.BoardGameSearched;
import util.Database;
import util.JsonConvert;
import util.XMLtoJSON;
import util.httpRequest.MyGetRequest;
import util.myexception.AccountNotExistException;
import util.myexception.NoSearchResultException;
import util.myexception.WrongPassWdException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardGameDao {
    private Database database;
    //private BoardGameFetched boardGameFetched;

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
     * 查询top100
     *
     * @return
     */
    public ArrayList<BoardGameModel> Browser() {
        String sql = "SELECT * FROM boardgame order by geekrating limit 100";
        return null;
    }
}
