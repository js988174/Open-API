package com.project.api.controller;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.service.MemberService;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    @PostMapping("/member/join")
    public MemberVo join(@RequestBody MemberVo memberVo){
        memberService.createMember(memberVo);
        return memberVo;
    }
    @GetMapping("/member/find")
    public Object find(Long id){
        Optional<MemberEntity> memberId = memberRepository.findById(id);
        return memberId;
    }

    @GetMapping("/member/findString")
    public Object findString(String name){
        MemberEntity member = memberRepository.findByName(name);

        return member;
    }
}
