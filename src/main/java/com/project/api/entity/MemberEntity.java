package com.project.api.entity;

import com.project.api.vo.MemberVo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String id;
    private String password;
    private String name;
    private String role ;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    public List<BoardEntity> boardList;


    @Builder
    public MemberEntity(String id, String password, String name,String role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role  = role;

    }
}
