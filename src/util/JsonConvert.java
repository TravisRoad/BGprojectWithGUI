package util;

import com.google.gson.Gson;
import model.boardgame.BoardGame;
import model.boardgame.HotBoardGame;

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

    public void to(String json) {
        Gson gson = new Gson();
        BoardGame hbg = gson.fromJson(json, BoardGame.class);
        System.out.println(hbg);
    }

    public static void main(String[] args) {
        JsonConvert.test();
    }


}
