package com.project.api.service;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoderCustom passwordEncoder;
    private static final String password = "1234";
    private static final String id = "테스트 계정1";
    private static final String name = "이름1";

//    @BeforeEach
//    void clean() {
//        memberRepository.deleteAll();
//    }
    @Test
    @DisplayName("회원 생성")
    @Rollback(value = false)
    void createMember() {
        
        
        String encodedPassword = passwordEncoder.encode(password);
        // given
        MemberVo memberVo = MemberVo.builder()
                .id(id)
                .password(encodedPassword)
                .name(name)
                .build();

        // when
        memberService.createMember(memberVo);

        // then
        Member member = memberRepository.findById(id);
        assertTrue(passwordEncoder.matches(password, encodedPassword));
        assertEquals(name, member.getName());
    }

    @Test
    @DisplayName("회원 로그인")
    public void signin() {
        //given
        MemberVo memberVo = MemberVo.builder()
                .id(id)
                .password(password)
                .build();

        
        //when
        UserDetailDTO userDetails  = (UserDetailDTO) memberService.signin(memberVo);

        //then
        assertTrue(passwordEncoder.matches(password, userDetails.getPassword()));
        assertEquals(userDetails.getUsername() , id);
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByName() {
    }
}