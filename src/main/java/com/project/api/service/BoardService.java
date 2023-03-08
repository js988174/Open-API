package com.project.api.service;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.repository.BoardRepository;
import com.project.api.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardVo saveBoard(BoardVo boardVo , Member member){

        Board board = boardVo.getBoardEntity(member);

        boardRepository.save(board);
        return boardVo;
    }

    public List<BoardVo> boardList(){
        List<BoardVo> boardVoList = boardRepository.findAll().stream()
                .map(board -> new BoardVo(board)).collect(Collectors.toList());
        return boardVoList;
    }
}
