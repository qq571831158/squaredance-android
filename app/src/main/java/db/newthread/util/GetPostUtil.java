package db.newthread.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.util.Log;

public class GetPostUtil {
	
    static InputStream inputStream = null;
    static HttpURLConnection urlConnection = null;
    static BufferedReader reader=null;
    static String response="";
    /**
     * 向url post json数据
     * @param Url
     * @param object
     * @return
     */
    public static  String UrlPostJson(String Url,JSONObject object){
        try {
            URL url = new URL(Url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(20 * 1000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            String json = String.valueOf(object);
            //Log.d("json",json);
            // Log.d("json", String.valueOf(Charset.forName("utf-8").encode(json)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            bw.write(json);
            bw.flush();
            bw.close();
            Log.d("code", String.valueOf(urlConnection.getResponseCode()));
            if (urlConnection.getResponseCode() == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                response = builder.toString();
                Log.d("response200", response);
            } else {
                Log.d("response400", urlConnection.getRequestMethod());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

}
