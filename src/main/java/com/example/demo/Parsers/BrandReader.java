package com.example.demo.Parsers;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class BrandReader {
    @SneakyThrows
    public static void BrandFetchGetter(String my_url, CompanyInfo info) {
        String API_KEY = "Bearer NpMXuaEYgwNGyJPMNaEIiEc8PBPQLy9fCQE68lAj08k=";
//        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='"+my_url+"'", String.valueOf(StandardCharsets.UTF_8));
//        URL url = new URL("https://api.brandfetch.io/v2/brands/" + query);
        URL url = new URL("https://api.brandfetch.io/v2/brands/" + my_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        System.out.println(jsonObject);
        try {
            JSONArray f_url = jsonObject.getJSONArray("links");
            for (int i = 0; i < f_url.length(); i++) {
                System.out.println(f_url.getJSONObject(i).get("name"));
                String obj_name = f_url.getJSONObject(i).get("name").toString();
                if(Objects.equals(obj_name, "twitter") && Objects.equals(info.getTwitter(), "")){
                    info.setTwitter(f_url.getJSONObject(i).get("url").toString());
                }
                if(Objects.equals(obj_name, "facebook") && Objects.equals(info.getFacebook(), "")){
                    info.setFacebook(f_url.getJSONObject(i).get("url").toString());
                }
            }
        }
        catch (JSONException e){
            System.out.println("BFG twitter/facebook error occured!");
        }
        try {
            JSONArray f_url = jsonObject.getJSONArray("links");
            for (int i = 0; i < f_url.length(); i++) {
                System.out.println(f_url.getJSONObject(i).get("name"));
                String obj_name = f_url.getJSONObject(i).get("name").toString();
                if(Objects.equals(obj_name, "twitter") && Objects.equals(info.getTwitter(), "")){
                    info.setTwitter(f_url.getJSONObject(i).get("url").toString());
                }
                if(Objects.equals(obj_name, "facebook") && Objects.equals(info.getFacebook(), "")){
                    info.setFacebook(f_url.getJSONObject(i).get("url").toString());
                }
            }
        }
        catch (JSONException e){
            System.out.println("BFG twitter/facebook error occured!");
        }
        try {
            String aa = jsonObject.getJSONArray("logos").getJSONObject(0).getJSONArray("formats").getJSONObject(0).get("src").toString();
            if(!Objects.equals(aa, "") && info.getLogos().size() == 0){
                info.setLogos(new ArrayList<>(Collections.singletonList(aa)));
            }
        }
        catch (JSONException|IndexOutOfBoundsException e){
            System.out.println("BFG no logos found!");
        }
        try {
            String aa = jsonObject.getJSONArray("images").getJSONObject(0).getJSONArray("formats").getJSONObject(0).get("src").toString();
            if(!Objects.equals(aa, "") && info.getIcons().size() == 0){
                info.setIcons(new ArrayList<>(Collections.singletonList(aa)));
            }
        }
        catch (JSONException|IndexOutOfBoundsException e){
            System.out.println("BFG no images found!");
        }
    }

    public static void main(String[] args) {
        String url = "jetbrains.com";
        CompanyInfo info = new CompanyInfo();
        BrandReader.BrandFetchGetter(url, info);
    }

}
