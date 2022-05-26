package com.products.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public class GetingData extends Thread {
    private Map<String, ArrayList<String>> multiValueMap = new TreeMap<String, ArrayList<String>>();
    private final StringBuilder responseContent = new StringBuilder();
    private String data = "";
    private String name;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public GetingData(String name) {
        this.name = name;
    }

    public Map<String, ArrayList<String>> getMultiValueMap() {
        return multiValueMap;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(name);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            System.out.println(timeStamp);
            String commands = "curl -s -w %{time_total} -o /dev/null " + name;
            Process process = Runtime.getRuntime().exec(commands);
            BufferedReader responseTimeReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String responseTime;
            responseTime = responseTimeReader.readLine();
            System.out.println("URL: " + name + "\nResponse time from server: " + responseTime + " secconds " + "\n_______________________________");
            process.destroy();
            int status = conn.getResponseCode();
            BufferedReader reader;
            String line = new String();
            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                    //System.out.println(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                data = responseContent.toString();

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(data);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject productsArray = (JSONObject) jsonArray.get(i);
                    long productId = (long) productsArray.get("product_id");
                    double productPrice = (double) productsArray.get("price");
                    String a = "product_id" + " : " + Integer.parseInt(String.valueOf(productId));
                    String b = String.valueOf(df.format(productPrice));
                    if (multiValueMap.containsKey(a)) {
                        multiValueMap.get(a).add(b);
                        //System.out.println(multiValueMap.get(a));
                    } else {
                        multiValueMap.put(a, new ArrayList<String>());
                        multiValueMap.get(a).add("price: " + b);
                    }
                }

            }
            conn.disconnect();

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}
