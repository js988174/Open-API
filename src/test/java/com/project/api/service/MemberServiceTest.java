package com.project.api.service;

import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.Member;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoderCustom passwordEncoder;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }
    @Test
    @DisplayName("회원 생성")
    @Rollback(value = false)
    void createMember() {

        String password = "1234";
        String encodedPassword = passwordEncoder.encode(password);

        // given
        MemberVo memberVo = MemberVo.builder()
                .id("테스트 계정2")
                .password(encodedPassword)
                .name("테스트 이름1")
                .build();

        // when
        memberService.createMember(memberVo);

        // then
        Member member = memberRepository.findById("테스트 계정2");
        assertTrue(passwordEncoder.matches(password, encodedPassword));
        assertEquals("테스트 이름1", member.getName());
    }

}