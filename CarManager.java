package com.example.appmobie;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarManager {
    private static final String BASE_URL = "http://192.168.119.175/qlbx/phpCar/";

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
    public String addCar(String plate, String bus, String driver, String age, String sex, String phone, String fare, String route, String sit, String sevice) {
        String urlString = BASE_URL + "addCar.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("bienso", plate);
            postData.put("tenxe", bus);
            postData.put("tentaixe", driver);
            postData.put("tuoi", age);
            postData.put("gioitinh", sex);
            postData.put("sdt", phone);
            postData.put("phi", fare);
            postData.put("tuyen", route);
            postData.put("ghengoi", sit);
            postData.put("mdv", sevice);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String updateCar( String plate, String bus, String driver, String age, String sex, String phone, String fare, String route, String sit, String sevice) {
        String urlString = BASE_URL + "updateCar.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("bienso", plate);
            postData.put("tenxe", bus);
            postData.put("tentaixe", driver);
            postData.put("tuoi", age);
            postData.put("gioitinh", sex);
            postData.put("sdt", phone);
            postData.put("phi", fare);
            postData.put("tuyen", route);
            postData.put("ghengoi", sit);
            postData.put("mdv", sevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String deleteCar(int id) {
        String urlString = BASE_URL + "deleteCar.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("bienso", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);

    }


}
