package com.project.api.service;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.Member;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoderCustom passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private static final String password = "1234";
    private static final String id = "테스트 계정1";
    private static final String name = "이름1";

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }
    @Test
    @DisplayName("회원 생성")
    @Rollback(value = false)
    void createMember() {
        
        
        // given
        MemberVo memberVo = MemberVo.builder()
                .id(id)
                .password(password)
                .name(name)
                .build();

        // when
        memberService.createMember(memberVo);

        // then
        Member member = memberRepository.findById(id);
        assertTrue(passwordEncoder.matches(password, member.getPassword()));
        assertEquals(name, member.getName());
    }

    @Test
    @DisplayName("회원 로그인")
    public void signin() {
        //given
        createMember();
        MemberVo memberVo = MemberVo.builder()
                .id(id)
                .password(password)
                .build();

        //when
        String token  = memberService.signin(memberVo);

        //then
        try {
            assertTrue((jwtTokenProvider.validateToken(token)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByName() {
    }
}