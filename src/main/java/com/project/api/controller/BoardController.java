package com.project.api.controller;

import com.project.api.entity.Member;
import com.project.api.service.BoardService;
import com.project.api.vo.BoardVo;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public BoardVo write(@RequestBody @Valid BoardVo boardVo){
        Member member = ((UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
        System.out.println(member);
        return boardService.saveBoard(boardVo, member);
    }

    @GetMapping("/list")
    public List<BoardVo> boardList(){
        return boardService.boardList();
    }
}
