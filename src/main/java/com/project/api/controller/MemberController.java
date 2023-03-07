package com.project.api.controller;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.service.CustomUserDetailService;
import com.project.api.service.MemberService;
import com.project.api.service.SignService;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final MemberService memberService;
    private final CustomUserDetailService userDetailService;
    private final SignService signService;

    @PostMapping("/create")
    public MemberVo join(@RequestBody MemberVo memberVo){
        memberService.createMember(memberVo);
        return memberVo;
    }
    @GetMapping("/find")
    public Object find(Long id){
        Optional<MemberEntity> memberId = memberRepository.findById(id);
        return memberId;
    }

    @GetMapping("/findString")
    public Object findString(String name){
        MemberEntity member = memberRepository.findByName(name);

        return member;
    }

    @PostMapping("/login")
    public MemberVo login(@RequestBody MemberVo memberVo) {
        signService.signin(memberVo);
        return memberVo;
    }
}
