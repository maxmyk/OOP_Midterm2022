package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping(value = "/")
    public String aName() {
        return "index.html";
    }
}
