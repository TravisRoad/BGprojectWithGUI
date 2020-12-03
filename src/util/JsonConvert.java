package util;

import com.google.gson.Gson;
import model.boardgamefetched.BoardGameFetched;
import model.boardgamefetched.HotBoardGame;
import model.search.BoardGameSearched;

/**
 * 用于装换Json字符串为Java Beans对象
 */
public class JsonConvert {

    static void test() {
        String json = "{\n" +
                "    \"rank\": 1,\n" +
                "    \"gameId\": 306040,\n" +
                "    \"name\": \"Merv: The Heart of the Silk Road\",\n" +
                "    \"thumbnail\": \"https://cf.geekdo-images.com/y6DEV5Qf6hyXpxRlafgOWw__thumb/img/aVefQAlc_n4d3GCToQYMluFpgBs=/fit-in/200x150/filters:strip_icc()/pic5314414.jpg\",\n" +
                "    \"yearPublished\": 2020\n" +
                "  }";
        Gson gson = new Gson();
        HotBoardGame hbg = gson.fromJson(json, HotBoardGame.class);
        System.out.println(hbg);
    }

    public static BoardGameFetched convert2BoardGameFetched(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, BoardGameFetched.class);
    }

    /**
     * 将搜索API获得的json字符串转化为BoardGameSearched对象
     *
     * @param json json字符串
     * @return 对象
     */
    public static BoardGameSearched convert2BoardgameSearched(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, BoardGameSearched.class);
    }

    public static void main(String[] args) {
        JsonConvert.test();
    }


}
