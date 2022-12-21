package com.example.demo.Controllers;

import com.example.demo.Magic.DBHandler;
import com.example.demo.Parsers.BrandReader;
import com.example.demo.Parsers.CompanyInfo;
import com.example.demo.Parsers.LogoGetter;
import com.example.demo.Parsers.PDLReader;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path="/result", method=RequestMethod.POST)
public class MainController {
    @GetMapping
    @SneakyThrows
    public static String getPayments(@RequestParam("domain") String url) {
        url = url.toLowerCase();
        url = url.replaceAll("http://", "");
        url = url.replaceAll("https://", "");
        url = url.replaceAll("www.", "");
        DBHandler db = DBHandler.getInstance();
        if(!db.checkData(url)){
            CompanyInfo info = new CompanyInfo();
            LogoGetter.logoGetter("https://"+url, info);
            PDLReader.PDLGetter(url, info);
            BrandReader.BrandFetchGetter(url, info);
            db.insertData(url, info.toString());
            return "<h1>That's exactly what You were looking for =)</h1><br><h3>" + info.toString().replaceAll("\",\"",
                    "\"<br>\"").replaceAll("],\"",
                    "]<br>\"") + "</h2><form>\n" +
                    " <input type=\"button\" value=\"Go back!\" onclick=\"history.back()\">\n" +
                    "</form>";
        }
        else{
            String data = db.getData(url);
            return "<h1>That's exactly what You were looking for =)</h1><br><h3>" + data.replaceAll("\",\"",
                    "\"<br>\"").replaceAll("],\"",
                    "]<br>\"") + "</h2><form>\n" +
                    " <input type=\"button\" value=\"Go back!\" onclick=\"history.back()\">\n" +
                    "</form>";
        }

    }
}