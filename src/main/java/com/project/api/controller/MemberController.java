package com.project.api.controller;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    @PostMapping("/join")
    public MemberEntity join(MemberVo memberVo){

        MemberEntity member = MemberEntity.builder().memberVo(memberVo).build();
        memberRepository.save(member);
        return member;
    }

}
