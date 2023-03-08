package com.project.api.service;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 생성")
    void createMember() {
        // given

        MemberVo memberVo = MemberVo.builder()
                .id("테스트 계정1")
                .password("1234")
                .name("테스트 이름1")
                .build();


        // when
        memberService.createMember(memberVo);

        // then
        MemberEntity memberEntity = memberRepository.findAll().get(0);
        assertEquals("테스트 계정1.", memberEntity.getId());
        assertEquals("1234.", memberEntity.getPassword());
        assertEquals("테스트 이름1.", memberEntity.getName());
    }

}