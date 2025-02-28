package com.example.appmobie;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeviceManager {
    private static final String BASE_URL = "http://192.168.119.175/qlbx/phpSev/";

    private String sendPostRequest(String urlString, JSONObject postData) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = postData.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    return response.toString();
                }
            } else {
                return "HTTP Error: " + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    public String addSev(String id, String name, String fare) {
        String urlString = BASE_URL + "addSev.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("madv", id);
            postData.put("tendv", name);
            postData.put("giave", fare);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String updateSev(String id, String name, String fare) {
        String urlString = BASE_URL + "updateSev.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("madv", id);
            postData.put("tendv", name);
            postData.put("giave", fare);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String deleteSev(int id) {
        String urlString = BASE_URL + "deleteSev.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("madv", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);

    }
}
