package com.project.api.entity;

import com.project.api.vo.MemberVo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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

    @Builder
    public MemberEntity(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
