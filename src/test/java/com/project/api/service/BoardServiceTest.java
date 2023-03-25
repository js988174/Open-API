package com.project.api.service;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.Board;
import com.project.api.response.BoardListResult;
import com.project.api.vo.BoardListVo;
import com.project.api.vo.BoardVo;
import com.project.api.vo.MemberVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private static final String password = "1234";
    private static final String name = "테스트 계정이름1";
    private static final String id = "테스트 계정1";
    private static final String content = "1234";
    private static final String title = "테스트 계정1";

    @Test
    void login() {
        MemberVo memberVo = MemberVo.builder()
                .id(id)
                .name(name)
                .password(password).build();

        String token = memberService.signin(memberVo);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @Test
    @Rollback
    void saveBoard() {
        //given
        login();
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("테스트 1글 제목");
        boardVo.setContent("테스트 1글 내용");
        //when

        Long id = boardService.saveBoard(boardVo);

        //then
        Board board = boardService.findBoard(id);

        assertEquals(board.getTitle(), boardVo.getTitle());
        assertEquals(board.getContent(), boardVo.getContent());
    }
    @Rollback
    @Test
    void boardList() {

        //given
        login();
        for(int i = 1; i <=10 ; i++){
            BoardVo boardVo = new BoardVo();
            boardVo.setTitle("테스트 "+i+"글 제목");
            boardVo.setContent("테스트"+i+"글 내용");

            System.out.println(boardVo);
            boardService.saveBoard(boardVo);
        }
        PageRequest pageable = PageRequest.of(0, 9);

        //when
        BoardListResult<Integer , List<BoardListVo>> result = boardService.boardList(pageable);

        //then
        assertEquals(10, result.getTotalCount());

    }



    @Test
    public void deleteBoard() {
    }

    @Test
    public void updateBoard() {
    }
}