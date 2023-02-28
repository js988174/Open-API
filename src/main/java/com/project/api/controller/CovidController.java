package com.project.api.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public Object covid() throws Exception {

        String apiURL = "https://api.corona-19.kr/korea/";
        String authKey = "berRulMsJy2PTaBXSYv1j9hExcnIdQqDp";
        String result = "";

        String urlBuilder = apiURL + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + authKey;

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

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject =(JSONObject) jsonParser.parse(sb.toString());

        return jsonObject;
    }

    @GetMapping("/covid/vaccine")
    public Object covidVaccine() throws Exception {

        String apiURL = "https://api.corona-19.kr/korea/vaccine/";
        String authKey = "berRulMsJy2PTaBXSYv1j9hExcnIdQqDp";
        String result = "";

        String urlBuilder = apiURL + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + authKey;

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

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject =(JSONObject) jsonParser.parse(sb.toString());

        return jsonObject;
    }

}
