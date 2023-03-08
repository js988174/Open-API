package com.project.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String id;
    private String password;
    private String name;
    private String role ;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    @JsonIgnore
    public List<Board> boardList;


    @Builder
    public Member(String id, String password, String name, String role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role  = "USER";

    }
}
