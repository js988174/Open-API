package com.project.api.service;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.Board;
import com.project.api.response.BoardListResult;
import com.project.api.vo.BoardListVo;
import com.project.api.vo.BoardVo;
import com.project.api.vo.MemberVo;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    @Before
    void deleteAll(){

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
    @Transactional(readOnly = false)
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
        assertEquals(11, result.getTotalCount());

    }



    @Test
    @Rollback
    @Transactional
    public void deleteBoard() {

        //given
        login();
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("테스트 1글 제목");
        boardVo.setContent("테스트 1글 내용");
        Long id = boardService.saveBoard(boardVo);

        //when
        Long id2 = boardService.deleteBoard(id);
        Board board = boardService.findBoard(id2);
        //then
        assertEquals(id,id2);
        assertTrue(board.isDeleteFlag());
    }

    @Test
    @Rollback
    @Transactional
    public void updateBoard() {


        login();
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("테스트 1글 제목");
        boardVo.setContent("테스트 1글 내용");
        Long id = boardService.saveBoard(boardVo);
        BoardVo boardVo2 = new BoardVo();
        boardVo2.setTitle("테스트 1글 제목 수정");
        boardVo2.setContent("테스트 1글 내용 수정");

        //when
        boardService.updateBoard(id , boardVo2);
        Board findBoard = boardService.findBoard(id);


        //then
        Assertions.assertEquals("테스트 1글 제목 수정", findBoard.getTitle());
        Assertions.assertEquals(findBoard.getContent(), boardVo2.getContent());
    }
}