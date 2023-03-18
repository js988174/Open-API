package com.project.api.controller;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.Member;
import com.project.api.repository.MemberRepository;
import com.project.api.service.MemberService;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;
import lombok.AllArgsConstructor;
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
    public Result join(@RequestBody MemberVo memberVo){

        return new Result(memberService.createMember(memberVo));
    }
    @GetMapping("/find")
    public Result find(Long id){
        Optional<Member> memberId = memberRepository.findById(id);
        return new Result(memberId.get());
    }

//    @GetMapping("/findString")
//    public Object findString(String name){
//        Member member = memberRepository.findByName(name);
//
//        return new Result(memberService.createMember(member));;
//    }

    @PostMapping("/login")
    public Result login(@RequestBody MemberVo memberVo) {
        String token = memberService.signin(memberVo);

        return new Result<>(token);
        //get >주소에 정보를 담아
    }

    @PostMapping("/loginInfo")
    public Object loginInfo() {
        return new Result(
                (
                    (UserDetailDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                ).getMember()

        );
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T Result;
    }
}
