package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class HelloController {

    // Define a simple hello world API

    @GetMapping("/hello")
    public String getMethodName() {

        return "Hello World";
    }

}
