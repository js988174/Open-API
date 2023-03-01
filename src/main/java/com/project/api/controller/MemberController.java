package com.project.api.controller;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        Optional<MemberEntity> member = memberRepository.findById(no);

        System.out.println(member.get().toString());;
        return member.get();
    }

    @GetMapping("/findString")
    public Object findString(String name){
        MemberEntity member = memberRepository.findByName(name);

        return member;
    }
}
