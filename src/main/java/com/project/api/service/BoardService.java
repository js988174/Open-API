package com.project.api.service;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.exception.BoardNotFound;
import com.project.api.repository.BoardRepository;
import com.project.api.vo.BoardListVo;
import com.project.api.vo.BoardVo;
import com.project.api.vo.ListResult;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public ListResult boardList(Pageable pageable){
        Page<Board> boardVoList = boardRepository.findAll(pageable);
        ListResult<Integer , List<BoardListVo>> result = new ListResult<>(boardVoList.getTotalPages() ,
                boardVoList.toList().stream().map(BoardListVo::new)
                        .collect(Collectors.toList()));

        return result;
    }
    public Board findBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

       boardRepository.findById(id).get();
        return board;
    }
    public void deleteBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        board.setDelete(true);
    }

    public void updateBoard(Long id, BoardVo boardVo){
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        board = this.findBoard(boardVo.getBoardNo());
        board.update(boardVo.getTitle(),board.getContent());

    }
}
