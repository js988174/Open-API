package com.project.api.vo;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardVo {
    private Long boardNo;
    private String title;
    private String content;


    public Board getBoardEntity(Member member) {
        return Board.builder()
                .member(member)
                .title(getTitle())
                .content(getContent())
                .regDate(LocalDate.now())
                .build();
    }

    public BoardVo(Board board){
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
