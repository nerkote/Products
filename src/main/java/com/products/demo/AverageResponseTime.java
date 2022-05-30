package com.products.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AverageResponseTime {


    public static JSONArray getResponseTime() {
        long apiOne=0;
        long apiTwo=0;
        long apiThree = 0;
        long apiFour = 0;
        int a=0;
        int b=0;
        int d=0;
        int e=0;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectTwo = new JSONObject();
        JSONObject jsonObjectThree = new JSONObject();
        JSONObject jsonObjectFour = new JSONObject();
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        Connection c = null;
        Statement stmt = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/products",
                            "postgres", "motorola96");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RESPONSES;");
            while (rs.next()) {
                String url = rs.getString("url");
                String date = rs.getString("request_executed_at");
                long response_time = rs.getLong("response_time");
                if (date.contains(timeStamp)) {
                    if (url.contains("api1")) {
                        apiOne += response_time;
                        a++;
                    } else if (url.contains("api2")) {
                        apiTwo += response_time;
                        b++;
                    } else if (url.contains("api3")) {
                        apiThree += response_time;
                        d++;
                    } else if (url.contains("api4")) {
                        apiFour += response_time;
                        e++;
                    }
                }
            }
            apiOne=apiOne/a;
            apiTwo=apiTwo/b;
            apiThree=apiThree/d;
            apiFour=apiFour/e;
            rs.close();
            stmt.close();
            c.close();
            jsonObject.put("average_reponse_time", apiOne);
            jsonObject.put("url", "https://simple-scala-api.herokuapp.com/api1");

            jsonObjectTwo.put("average_reponse_time", apiTwo);
            jsonObjectTwo.put("url", "https://simple-scala-api.herokuapp.com/api2");

            jsonObjectThree.put("average_reponse_time", apiThree);
            jsonObjectThree.put("url", "https://simple-scala-api.herokuapp.com/api3");

            jsonObjectFour.put("average_reponse_time", apiFour);
            jsonObjectFour.put("url", "https://simple-scala-api.herokuapp.com/api4");

            jsonArray.add(jsonObject);
            jsonArray.add(jsonObjectTwo);
            jsonArray.add(jsonObjectThree);
            jsonArray.add(jsonObjectFour);

        } catch (Exception f) {
            System.err.println(f.getClass().getName() + ": " + f.getMessage());
            System.exit(0);
        }
        return jsonArray;
    }
}