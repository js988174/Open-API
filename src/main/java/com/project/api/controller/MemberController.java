package com.project.api.controller;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.Member;
import com.project.api.repository.MemberRepository;
import com.project.api.service.MemberService;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Member join(@RequestBody MemberVo memberVo){
        return memberService.createMember(memberVo);
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
        UserDetailDTO userDetailDTO =(UserDetailDTO)  memberService.signin(memberVo);
        MemberLoginResultDTO memberLoginResultDTO = new MemberLoginResultDTO();
        memberLoginResultDTO.id = userDetailDTO.getMember().getId();
        memberLoginResultDTO.name = userDetailDTO.getMember().getName();
        memberLoginResultDTO.token = jwtTokenProvider.createToken(userDetailDTO.getUsername(), userDetailDTO.getRoles());
        return ResponseEntity.ok(memberLoginResultDTO);
    }

    @PostMapping("/loginInfo")
    public Object loginInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Data
    class MemberLoginResultDTO{
        String id;
        String name;
        String token;
    }
}
