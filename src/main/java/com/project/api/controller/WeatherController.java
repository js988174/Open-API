package com.project.api.controller;


import com.project.api.apiclass.WeatherAPI;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherAPI weatherAPI;

    @GetMapping("/test")
    public Object test() throws Exception {
        return weatherAPI.test();
    }

}