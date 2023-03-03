package com.project.api.service;

import com.project.api.entity.BoardEntity;
import com.project.api.entity.MemberEntity;
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

    public void saveBoard(BoardVo boardVo , MemberEntity member){

        BoardEntity boardEntity = boardVo.getBoardEntity(member);

        boardRepository.save(boardEntity);
    }

    public List<BoardVo> boardList(){
        List<BoardVo> boardVoList = boardRepository.findAll().stream()
                .map(boardEntity -> new BoardVo(boardEntity)).collect(Collectors.toList());
        return boardVoList;
    }
}
