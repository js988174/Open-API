package com.project.api.controller;

import com.project.api.service.BoardService;
import com.project.api.vo.BoardVo;
import com.project.api.response.BoardListResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public Result write(@RequestBody @Valid BoardVo boardVo){
        return new Result(boardService.saveBoard(boardVo)); //Long
    }

    @GetMapping("/list")
    public BoardListResult boardList(
            @PageableDefault(sort = "id", size = 5, direction = Sort.Direction.DESC)
            Pageable pageable){
        return boardService.boardList(pageable); // BoardListVo
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        boardService.deleteBoard(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody BoardVo boardVo){
        boardService.updateBoard(boardVo);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T Result;
    }
}
