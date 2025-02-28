package com.example.appmobie;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmployeeManager {
    private static final String BASE_URL = "http://192.168.119.175/qlbx/phpEmployee/";

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
    public String addEmployee(String id, String name, int age, String sex, String phone, String salary, String position) {
        String urlString = BASE_URL + "addEmployee.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("manv", id);
            postData.put("ten", name);
            postData.put("tuoi", age);
            postData.put("gioitinh", sex);
            postData.put("sdt", phone);
            postData.put("luong", salary);
            postData.put("chucvu", position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String updateEmployee(String id, String name, int age, String sex, String phone, String salary, String position) {
        String urlString = BASE_URL + "updateEmployee.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("manv", id);
            postData.put("ten", name);
            postData.put("tuoi", age);
            postData.put("gioitinh", sex);
            postData.put("sdt", phone);
            postData.put("luong", salary);
            postData.put("chucvu", position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String deleteEmployee(int id) {
        String urlString = BASE_URL + "deleteEmployee.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("manv", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);

    }
}
