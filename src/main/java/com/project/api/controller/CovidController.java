package com.project.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class CovidController {


    @GetMapping("/covid")
    public Object covid() throws IOException {

        String apiURL = "http://apis.data.go.kr/1352000/ODMS_COVID_04/callCovid04Api";
        String authKey = "CGrt6129vP2mSoNsg73Pp1RyTPaHgOZC11sSwEW5Tgs9%2FWSVG5B%2B8dyZ4ICBhX%2BuXECar6OulkDMbu0XQ3e83A%3D%3D";
        String pageNo = "1";
        String numOfRows = "10";
        String std_day = "2021-12-15";
        String gubun = "경기";
        String result = "";

        String urlBuilder = apiURL + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + authKey +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pageNo) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(numOfRows) +
                "&" + URLEncoder.encode("std_day", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(std_day, StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("gubun", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(gubun);


        URL url = new URL(urlBuilder);
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        result = sb.toString();

        return result;
    }

}
