package com.project.api.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private LocalDate regDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private Member member;

    @Builder
    public Board(String title, String content, LocalDate regDay, Member member) {
        this.title = title;
        this.content = content;
        this.regDay = regDay;
        this.member = member;
    }
}
