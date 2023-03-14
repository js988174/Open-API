package com.project.api.controller;

import com.project.api.service.BoardService;
import com.project.api.vo.BoardVo;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public BoardVo write(@RequestBody BoardVo boardVo){
        UserDetailDTO userDetailDTO = (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return boardService.saveBoard(boardVo, userDetailDTO.getMember());
    }

    @GetMapping("/list")
    public List<BoardVo> boardList(){
        return boardService.boardList();
    }
}
