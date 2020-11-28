package util.httpRequest;

import util.JsonConvert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyGetRequest {
    private final String id_url_api = "https://bgg-json.azurewebsites.net/thing/";

    public static String GetRequest(String url) throws IOException {
        StringBuffer response = new StringBuffer();
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlForGetRequest.openConnection();
        httpURLConnection.setRequestMethod("GET");
        //conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
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
        return response.toString();
    }

    public static void main(String[] args) {
        try {
            String a = MyGetRequest.GetRequest("https://bgg-json.azurewebsites.net/thing/31260");
            JsonConvert j = new JsonConvert();
            j.to(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
