package com.project.api.service;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.repository.BoardRepository;
import com.project.api.vo.BoardVo;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    public Long saveBoard(BoardVo boardVo){

        Member member =
                ((UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
        Board board = boardVo.getBoardEntity(member);
        boardRepository.save(board);

        return board.getBoardNo();
    }

    public List<BoardVo> boardList(){
        List<BoardVo> boardVoList = boardRepository.findAll().stream()
                .map(board -> new BoardVo(board)).collect(Collectors.toList());
        return boardVoList;
    }
    public Board findBoard(Long id){
        Board board = boardRepository.findById(id).get();
        return board;
    }
    public void deleteBoard(Long id){
        Board board = this.findBoard(id);
        board.setDelete(true);
    }

    public void updateBoard(BoardVo boardVo){
        Board board = this.findBoard(boardVo.getBoardNo());
        board.update(boardVo.getTitle(),board.getContent());

    }
}
