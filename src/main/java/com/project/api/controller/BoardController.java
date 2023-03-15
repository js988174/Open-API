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

        return boardService.saveBoard(boardVo);
    }

    @GetMapping("/list")
    public List<BoardVo> boardList(){
        return boardService.boardList();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){

        boardService.deleteBoard(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody BoardVo boardVo){
        boardService.updateBoard(boardVo);
    }

}
