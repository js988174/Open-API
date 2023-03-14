package com.project.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoderCustom passwordEncoder;
    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 생성")
    void memberCreate() throws Exception {
        //given
        String password = "1234";
        String encodedPassword = passwordEncoder.encode(password);

        MemberVo memberVo = MemberVo.builder()
                .id("테스트 계정2")
                .password(encodedPassword)
                .name("테스트 이름1")
                .build();


        String url = "http://localhost:" + port + "/api/member/create";

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(memberVo))
                        .with(csrf()))
                .andExpect(status().isOk());
    }



}