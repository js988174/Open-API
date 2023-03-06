package com.project.api.controller;

import com.project.api.entity.MemberEntity;
import com.project.api.service.BoardService;
import com.project.api.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

//    @PostMapping("/write")
//    public BoardVo write(@RequestBody BoardVo boardVo){
//        return boardService.saveBoard(boardVo, new MemberEntity("id","pw","김"));
//    }

    @GetMapping("/list")
    public List<BoardVo> boardList(){
        return boardService.boardList();
    }
}
