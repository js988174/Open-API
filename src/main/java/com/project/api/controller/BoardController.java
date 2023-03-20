package com.project.api.controller;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.service.BoardService;
import com.project.api.vo.BoardVo;
import com.project.api.vo.ListResult;
import com.project.api.vo.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Result write(@RequestBody @Valid BoardVo boardVo){
        return new Result(boardService.saveBoard(boardVo)); //Long
    }

    @GetMapping("/list")
    public ListResult boardList(
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
