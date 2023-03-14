package com.project.api.controller;

import com.project.api.entity.Member;
import com.project.api.service.BoardService;
import com.project.api.vo.BoardVo;
import lombok.RequiredArgsConstructor;
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
        return boardService.saveBoard(boardVo, new Member("id","pw","김"));
    }

    @GetMapping("/list")
    public List<BoardVo> boardList(){
        return boardService.boardList();
    }
}
