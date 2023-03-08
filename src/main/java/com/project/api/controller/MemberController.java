package com.project.api.controller;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.Member;
import com.project.api.repository.MemberRepository;
import com.project.api.service.MemberService;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/create")
    public MemberVo join(@RequestBody MemberVo memberVo){
        memberService.createMember(memberVo);
        return memberVo;
    }
    @GetMapping("/find")
    public Object find(Long id){
        Optional<Member> memberId = memberRepository.findById(id);
        return memberId;
    }

    @GetMapping("/findString")
    public Object findString(String name){
        Member member = memberRepository.findByName(name);

        return member;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberVo memberVo) {
        memberService.signin(memberVo);
        UserDetailDTO userDetailDTO = (UserDetailDTO) memberService.loadUserByUsername(memberVo.getId());
        return ResponseEntity.ok(
                jwtTokenProvider.createToken(userDetailDTO.getUsername(), userDetailDTO.getRoles()));
    }
}
