package com.project.api.vo;

import com.project.api.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BoardEditorVo {
    private Long boardNo;
    private String title;
    private String content;

    @Builder
    public BoardEditorVo(Long boardNo,String title, String content) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
    }
}