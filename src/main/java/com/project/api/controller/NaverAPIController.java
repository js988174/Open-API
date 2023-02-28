package com.project.api.controller;

import com.project.api.apiclass.NaverOpenAPI;
import com.project.api.apiclass.WeatherAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NaverAPIController {
    private final NaverOpenAPI naverOpenAPI;

    @GetMapping("/movie")
    public Object test(String keyword) throws Exception {
        return naverOpenAPI.createSearch(keyword);
    }
}
