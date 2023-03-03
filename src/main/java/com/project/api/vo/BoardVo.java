package com.project.api.vo;

import com.project.api.entity.BoardEntity;
import com.project.api.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardVo {
    private Long boardNo;
    private String title;
    private String content;


    public BoardEntity getBoardEntity(MemberEntity member) {
        return BoardEntity.builder()
                .member(member)
                .title(getTitle())
                .content(getContent())
                .regDay(LocalDate.now())
                .build();
    }

    public BoardVo(BoardEntity boardEntity){

        this.boardNo = boardEntity.getBoardNo();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
    }
}
