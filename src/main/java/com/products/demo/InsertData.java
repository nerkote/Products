package com.products.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertData {

        public void insert(String url, String date, long response_time){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/products",
                            "postgres", "motorola96");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO RESPONSES (url,request_executed_at,response_time) "
                    + "VALUES ('"+url+"', '"+date+"', "+response_time+");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}