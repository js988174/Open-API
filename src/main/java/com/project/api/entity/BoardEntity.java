package com.project.api.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;
    private String content;
    private LocalDate regDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private MemberEntity member;

    @Builder
    public BoardEntity(String title, String content, LocalDate regDay,MemberEntity member) {
        this.title = title;
        this.content = content;
        this.regDay = regDay;
        this.member = member;
    }
}
