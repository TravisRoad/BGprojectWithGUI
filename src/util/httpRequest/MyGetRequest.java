package util.httpRequest;

import model.search.BoardGameSearched;
import util.JsonConvert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用于访问网络数据接口的类
 * 实现了request的get方法
 */
public class MyGetRequest {

    public static String request(String thingsToSearch) {
        String XMLapiString = "https://www.boardgamegeek.com/xmlapi/search?search=";
        String url = XMLapiString + thingsToSearch;
        return getResponds(url);
    }

    public static String request(int bg_id) {
        String jsonapiString = "https://bgg-json.azurewebsites.net/thing/";
        String url = jsonapiString + Integer.toString(bg_id);
        return getResponds(url);
    }

    public static String getResponds(String url) {
        StringBuffer response = new StringBuffer();
        try {
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlForGetRequest.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();

                System.out.println("JSON String Result" + response.toString());

            } else {
                System.out.println("GET NOT WORKED");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
