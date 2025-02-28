package com.example.appmobie;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserLogin {
    private static final String BASE_URL = "http://192.168.119.175/qlbx/phpTaikhoan/";

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
    public String loginUser(String username, String password) {
        String urlString = BASE_URL + "login.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String loginUserCus(String username, String password) {
        String urlString = BASE_URL + "loginCus.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String registerUser(String username, int phonenumber, String sex, String password) {
        String urlString = BASE_URL + "register.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("phone", phonenumber);
            postData.put("gioitinh", sex);
            postData.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

}
