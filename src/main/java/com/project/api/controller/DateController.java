package com.project.api.controller;

import com.project.api.vo.MemberVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {

    @GetMapping("/test")
    public MemberVo memberVo(){
        MemberVo memberVo = new MemberVo(1,"김경선");
        return memberVo;
    }
}
