package com.project.api.apiclass.navere;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class NaverOpenAPI {

    private static final String ClientID = "d8F7qH1zqcNCUpNkB1JQ" ;
    private static final String ClientKey = "XfVQ7gFjas";


    public Object createSearch(String keyword) throws Exception{


        String text = null;
        try {
            text = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=";    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과
        apiURL+=text;

        HashMap<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", ClientID);
        requestHeaders.put("X-Naver-Client-Secret", ClientKey);


        return connect(apiURL , requestHeaders);
    }

    private JSONObject connect(String apiUrl, Map requestHeaders){
        HttpURLConnection conn = connect(apiUrl);
        try {
            conn.setRequestMethod("GET");
            requestHeaders.forEach((o, o2) -> {
                conn.setRequestProperty((String) o,(String)o2);
            });

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(conn.getInputStream());
            } else { // 오류 발생
                return readBody(conn.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            conn.disconnect();
        }

    }



    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("MalformedURLException : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("IOException connect : " + apiUrl, e);
        }
    }


    private JSONObject readBody(InputStream inputStream) {
        BufferedReader rd;
        rd = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject =(JSONObject) jsonParser.parse(sb.toString());
            return jsonObject;
        }catch (IOException e){
            throw new RuntimeException("readBody IOException : " + e);
        }catch (ParseException parseException){
            throw new RuntimeException("readBody parseException : " + parseException);
        }
    }
}
