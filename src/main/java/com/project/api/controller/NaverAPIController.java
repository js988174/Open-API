package com.project.api.controller;

import com.project.api.apiclass.NaverOpenAPI;
import com.project.api.apiclass.WeatherAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NaverAPIController {
    private final NaverOpenAPI naverOpenAPI;

    @GetMapping("/test")
    public Object test() throws Exception {
        return naverOpenAPI.createSearch();
    }
}
