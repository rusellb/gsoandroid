package com.example.joycabildo.finalfinal;

import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by rusellbayote on 03/07/2018.
 */

public class Reader {

    JSONArray jsonArray;
    String json;

    public JSONArray getData(String url) {

        try {
            URL server = new URL(url);
            URLConnection connection = server.openConnection();
            BufferedReader is = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = is.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
            jsonArray = new JSONArray(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
