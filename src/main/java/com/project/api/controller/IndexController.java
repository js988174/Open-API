package com.project.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/swagger-ui")
    public String homeRedirect() {
        System.out.println("스웨거 진입");
        return "redirect:/swagger-ui/index.html";
    }
}
