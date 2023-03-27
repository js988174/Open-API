package com.project.api.controller;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WeatherController {

    @GetMapping("/weather")
    public Object weather() throws Exception {
        String apiURL = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String authKey = "bLT%2FnnGz655vMRYLmYPnULY15PcoXmH5TuMR0wOlyZhly6RlmQ%2Bcayy4f7yNYOVfbCvOxhxPv9pepFRdMeVkvw%3D%3D";

        //TODO 임시
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = new Date();

        String baseDate = StringUtils.substring(DateFormat.format(currentDate),0,8);

        String baseTime = "0500";
        String nx = "55"; // 위도
        String ny = "127";  // 경도

        StringBuilder urlBuilder = new StringBuilder(apiURL);

        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+authKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/

        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

//        String result= sb.toString();
//
//        //=======이 밑에 부터는 json에서 데이터 파싱해 오는 부분이다=====//
//
//        // response 키를 가지고 데이터를 파싱
//        JSONObject jsonObj_1 = new JSONObject(result);
//        String response = jsonObj_1.getString("response");
//
//        // response 로 부터 body 찾기
//        JSONObject jsonObj_2 = new JSONObject(response);
//        String body = jsonObj_2.getString("body");
//
//        // body 로 부터 items 찾기
//        JSONObject jsonObj_3 = new JSONObject(body);
//        String items = jsonObj_3.getString("items");
//        Log.i("ITEMS",items);
//
//        // items로 부터 itemlist 를 받기
//        JSONObject jsonObj_4 = new JSONObject(items);
//        JSONArray jsonArray = jsonObj_4.getJSONArray("item");
//
//        for(int i=0;i<jsonArray.length();i++){
//            jsonObj_4 = jsonArray.getJSONObject(i);
//            String fcstValue = jsonObj_4.getString("fcstValue");
//            String category = jsonObj_4.getString("category");
//
//            if(category.equals("SKY")){
//                weather = "현재 날씨는 ";
//                if(fcstValue.equals("1")) {
//                    weather += "맑은 상태로";
//                }else if(fcstValue.equals("2")) {
//                    weather += "비가 오는 상태로 ";
//                }else if(fcstValue.equals("3")) {
//                    weather += "구름이 많은 상태로 ";
//                }else if(fcstValue.equals("4")) {
//                    weather += "흐린 상태로 ";
//                }
//            }
//
//            if(category.equals("T3H") || category.equals("T1H")){
//                tmperature = "기온은 "+fcstValue+"℃ 입니다.";
//            }

        return jsonObject;

    }


}