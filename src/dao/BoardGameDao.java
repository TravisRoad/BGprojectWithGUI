package dao;

import model.boardgamefetched.BoardGameFetched;
import model.search.BoardGameSearched;
import util.Database;
import util.JsonConvert;
import util.XMLtoJSON;
import util.httpRequest.MyGetRequest;

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
     */
    public BoardGameSearched search(String thingstoSearch) {
        String xml = MyGetRequest.request(thingstoSearch);
        String json = XMLtoJSON.convert(xml);
        return JsonConvert.convert2BoardgameSearched(json);
    }

    /*public BoardGameFetched getBoardGameFetched() {
        return boardGameFetched;
    }

    public void setBoardGameFetched(BoardGameFetched boardGameFetched) {
        this.boardGameFetched = boardGameFetched;
    }*/

}
