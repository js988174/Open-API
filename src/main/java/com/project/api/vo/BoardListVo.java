package com.project.api.vo;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BoardListVo {

    private Long boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDate regDate;

    public BoardListVo(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getMember().getName();
        this.regDate = board.getRegDate();
    }
}