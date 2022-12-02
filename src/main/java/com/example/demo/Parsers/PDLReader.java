package com.example.demo.Parsers;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class PDLReader {
    @SneakyThrows
    public static void PDLGetter(String my_url, CompanyInfo info){
        String API_KEY = "f4f20e923805fde69255d83cad28c2071dbe76385d969a41cc4af1872ae21647";
        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='"+my_url+"'", String.valueOf(StandardCharsets.UTF_8));
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        System.out.println(jsonObject);
        try {
            int emp_count = jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count");
            if(Objects.equals(info.getEmployees(), 0))
                info.setEmployees(emp_count);
        }
        catch (JSONException e){
            System.out.println("PDL No emp_count data!");
        }
        try {
            String f_url = jsonObject.getJSONArray("data").getJSONObject(0).getString("facebook_url");
            if(Objects.equals(info.getTwitter(), ""))
                info.setFacebook(f_url);
        }
        catch (JSONException e){
            System.out.println("PDL No twitter data!");
        }
        try {
            String t_url = jsonObject.getJSONArray("data").getJSONObject(0).getString("twitter_url");
            if(Objects.equals(info.getTwitter(), ""))
                info.setTwitter(t_url);
        }
        catch (JSONException e){
            System.out.println("PDL No facebook data!");
        }
        try {
            String name = jsonObject.getJSONArray("data").getJSONObject(0).getString("name");
            if(Objects.equals(info.getName(), ""))
                info.setName(name);
        }
        catch (JSONException e){
            System.out.println("PDL No name data!");
        }
        try {
            JSONObject pre = jsonObject.getJSONArray("data").getJSONObject(0).getJSONObject("location");
            String addr = "";
            Iterator<String> keys = pre.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (pre.get(key) instanceof String) {
                    addr += key + ":" + pre.get(key) + ", ";
                    System.out.println(pre.get(key));
                }
            }
            if(Objects.equals(info.getAddress(), ""))
                info.setAddress(addr);
        }
        catch (JSONException e){
            System.out.println("PDL No location data!");
        }
    }
    public static void main(String[] args) {
        String url = "jetbrains.com";
        CompanyInfo info = new CompanyInfo();
        PDLReader.PDLGetter(url, info);
        System.out.println(info);
    }
}
