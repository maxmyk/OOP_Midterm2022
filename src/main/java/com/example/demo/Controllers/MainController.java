package com.example.demo.Controllers;

import com.example.demo.Parsers.BrandReader;
import com.example.demo.Parsers.CompanyInfo;
import com.example.demo.Parsers.LogoGetter;
import com.example.demo.Parsers.PDLReader;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/result", method=RequestMethod.POST)
public class MainController {
    @GetMapping
    public static String getPayments(@RequestParam("domain") String url) {
        url = url.toLowerCase();
        url = url.replaceAll("http://", "");
        url = url.replaceAll("https://", "");
        url = url.replaceAll("www.", "");
        CompanyInfo info = new CompanyInfo();
        LogoGetter.logoGetter("https://"+url, info);
        PDLReader.PDLGetter(url, info);
        BrandReader.BrandFetchGetter(url, info);
        String ans = "<h1>That's exactly what You were looking for =)</h1><br><h3>" + info.toString().replaceAll("\",\"",
                "\"<br>\"") + "</h2><form>\n" +
                " <input type=\"button\" value=\"Go back!\" onclick=\"history.back()\">\n" +
                "</form>";
        return ans;
    }
}