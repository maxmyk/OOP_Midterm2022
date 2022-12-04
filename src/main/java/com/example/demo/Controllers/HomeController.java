package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String aName() {
        return "index.html";
    }
}
