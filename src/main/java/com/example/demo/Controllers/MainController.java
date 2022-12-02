package com.example.demo.Controllers;

import com.example.demo.Parsers.BrandReader;
import com.example.demo.Parsers.CompanyInfo;
import com.example.demo.Parsers.LogoGetter;
import com.example.demo.Parsers.PDLReader;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/result", method= RequestMethod.POST)
public class MainController {
    @GetMapping
    public static String getPayments(@RequestParam("domain") String url) {
//        String url = "jetbrains.com";
//        String url = "ucu.edu.ua";
//        String url = "intel.com";
//        @RequestParam("domain") String username
        CompanyInfo info = new CompanyInfo();
        LogoGetter.logoGetter("https://"+url, info);
//        return info.toString();
        PDLReader.PDLGetter(url, info);
        BrandReader.BrandFetchGetter(url, info);
        return info.toString();
    }
}