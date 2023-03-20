package com.project.api.vo;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberListVo {

    private Long memberNo;
    private String id;
    private String password;
    private String name;

    public MemberListVo(Member member) {
        this.memberNo = member.getMemberNo();
        this.id = member.getId();
        this.password = member.getPassword();
        this.name = member.getName();
    }
}