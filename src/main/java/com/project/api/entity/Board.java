package com.project.api.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;


    private String title;
    private String content;
    private LocalDate regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private Member member;

    private boolean deleteFlag;

    @Builder
    public Board(String title, String content, LocalDate regDate, Member member){
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.member = member;
        this.deleteFlag = false;
    }
    public void update(String title,String content) {
        this.title = title;
        this.content = content;
    }
    public void setDelete(boolean delete) {
        this.deleteFlag = delete;
    }
}
