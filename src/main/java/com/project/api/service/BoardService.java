package com.project.api.service;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.exception.BoardNotFound;
import com.project.api.repository.BoardRepository;
import com.project.api.vo.BoardListVo;
import com.project.api.vo.BoardVo;
import com.project.api.response.BoardListResult;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public BoardListResult boardList(Pageable pageable){
        Page<Board> boardVoList = boardRepository.findAll(pageable);
        BoardListResult<Integer , List<BoardListVo>> result = new BoardListResult<>(
                boardVoList.getTotalPages() ,
                boardVoList.toList().stream().map(BoardListVo::new).collect(Collectors.toList())
        );

        return result;
    }
    public Board findBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

       boardRepository.findById(id);
        return board;
    }
    public Long deleteBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);
        Member member =
                ((UserDetailDTO) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()).getMember();
        
        if(!member.getId().equals(board.getMember().getId())){
            throw new RuntimeException("해당 글을 삭제 할 권한이 없습니다.");
        }
        board.setDelete(true);
        return board.getBoardNo();
    }
    @Transactional
    public Long updateBoard( BoardVo boardVo){
        System.out.println("어머머");
        Board board = boardRepository.findById(boardVo.getBoardNo())
                .orElseThrow(BoardNotFound::new);

        board.update(boardVo.getTitle(),board.getContent());

        System.out.println(board.getTitle());
        System.out.println(board.getContent());


        boardRepository.save(board);

        return board.getBoardNo();
    }
}
