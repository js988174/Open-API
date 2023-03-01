package com.project.api.controller;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    @PostMapping("/join")
    public MemberEntity join(@RequestBody MemberVo memberVo){

        MemberEntity member = MemberEntity.builder().memberVo(memberVo).build();
        memberRepository.save(member);
        return member;
    }
    @GetMapping("/find")
    public Object find(Long no){


        return memberRepository.findById(no);
    }
}
