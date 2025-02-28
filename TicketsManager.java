package com.example.appmobie;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TicketsManager {

    private static final String BASE_URL = "http://192.168.119.175/qlbx/phpTickets/";

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
    public String addTic(int IDve, int IDkhach, String Tenkhach, int agekh, String sexkh, String TenNV, int agenv, String sexnv, int phone, int fare, String route, String seat, String IDdv ) {
        String urlString = BASE_URL + "addTickets.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("mve", IDve);
            postData.put("mkh", IDkhach);
            postData.put("tenkh", Tenkhach);
            postData.put("tuoikh", agekh);
            postData.put("gtkh", sexkh);
            postData.put("tennv", TenNV);
            postData.put("tuoinv", agenv);
            postData.put("gtnv", sexnv);
            postData.put("number", phone);
            postData.put("giave", fare);
            postData.put("route", route);
            postData.put("seat", seat);
            postData.put("madv", IDdv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String updateTic(String IDve, int IDkhach, String Tenkhach, int agekh, String sexkh, String TenNV, int agenv, String sexnv, int phone, int fare, String route, String seat, String IDdv) {
        String urlString = BASE_URL + "updateTickets.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("mve", IDve);
            postData.put("mkh", IDkhach);
            postData.put("tenkh", Tenkhach);
            postData.put("tuoikh", agekh);
            postData.put("gtkh", sexkh);
            postData.put("tennv", TenNV);
            postData.put("tuoinv", agenv);
            postData.put("gtnv", sexnv);
            postData.put("number", phone);
            postData.put("giave", fare);
            postData.put("route", route);
            postData.put("seat", seat);
            postData.put("madv", IDdv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);
    }

    public String deleteTic(int id) {
        String urlString = BASE_URL + "deleteTickets.php";
        JSONObject postData = new JSONObject();
        try {
            postData.put("mve", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendPostRequest(urlString, postData);

    }
}
