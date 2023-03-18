package com.project.api.controller;

import com.project.api.apiclass.naver.NaverOpenAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    public Result test(String keyword) throws Exception {
        return new Result(naverOpenAPI.createSearch(keyword));
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T Result;
    }
}
