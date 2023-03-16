package com.project.api.service;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.vo.BoardVo;
import com.project.api.vo.MemberVo;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private static final String password = "1234";
    private static final String id = "테스트 계정1";
    private static final String content = "1234";
    private static final String title = "테스트 계정1";


    void login() {
        MemberVo memberVo = MemberVo.builder()
                .id(id)
                .password(password).build();
        String token = memberService.signin(memberVo);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @Test
    public void saveBoard() {
        //when
        login();

        // given
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("테스트 1글 제목");
        boardVo.setContent("테스트 1글 내용");
        Long id = boardService.saveBoard(boardVo);

        //then
        Board board = boardService.findBoard(id);

        assertEquals(board.getTitle(), boardVo.getTitle());
        assertEquals(board.getContent(), boardVo.getContent());
    }

    @Test
    public void boardList() {
    }

    @Test
    public void findBoard() {
    }

    @Test
    public void deleteBoard() {
    }

    @Test
    public void updateBoard() {
    }
}