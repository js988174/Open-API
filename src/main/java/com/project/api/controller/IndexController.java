package com.project.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/api")
    public String index(){
        System.out.println("인덱스 들어옴");
        return "/index";
    }
}
