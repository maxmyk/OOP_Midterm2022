package com.example.demo.Parsers;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class LogoGetter {


    static void getLogoElementFromLinkTag(Document doc, ArrayList<String> logos, ArrayList<String> icons) {
        // Finds logos in link tags in the document, and provides them to CompanyInfo lists
        Elements result = doc.head().select("link[rel]");
        if (result.first() != null){
            for(Element item : result) {
                String attribute = item.attr("rel");
                if (attribute.contains("icon")) {
                    icons.add(item.attr("href"));
                } else if (attribute.contains("logo")) {
                    logos.add(item.attr("href"));
                }
            }
        }
    }
    static void getLogoElementFromImgTag(Document doc, ArrayList<String> logos, ArrayList<String> icons) {
        // Finds logos in img tags in the document, and provides them to CompanyInfo lists
        Elements result = doc.select("img[src]");
        if (result.first() != null){
            for(Element item : result){
                String attribute = item.attr("src");
                if(attribute.contains("logo")){
                    logos.add(attribute);
                }else if(attribute.contains("icon")){
                    icons.add(attribute);
                }
            }
        }
    }

    public static CompanyInfo parse(String url, CompanyInfo info){
        try {
            final Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:107.0) Gecko/20100101 Firefox/107.0")
                    .timeout(30000)
                    .get();
            getLogoElementFromLinkTag(document, info.getLogos(), info.getIcons());
            getLogoElementFromImgTag(document, info.getLogos(), info.getIcons());

        }catch (Exception e){
            System.out.println("Unable to retrieve data from URL: " + url);
            System.out.println(e);
        }
        return info;
    }

    public static String test(){
        CompanyInfo info = new CompanyInfo();
        LogoGetter.parse("https://ucu.edu.ua", info);
        LogoGetter.parse("https://twitter.com", info);
        LogoGetter.parse("https://google.com.ua/", info);
        LogoGetter.parse("https://www.facebook.com", info);
        LogoGetter.parse("https://www.linkedin.com", info);
        LogoGetter.parse("https://www.instagram.com", info);
        LogoGetter.parse("https://www.youtube.com", info);
        LogoGetter.parse("https://www.reddit.com", info);
        LogoGetter.parse("https://www.tumblr.com", info);
        return info.toString();
    }
    public static void logoGetter(String url, CompanyInfo info){

        LogoGetter.parse(url, info);
    }
    public static void main(String[] args) {
        test();
    }
}
