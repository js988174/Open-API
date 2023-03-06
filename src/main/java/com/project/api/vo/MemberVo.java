package com.project.api.vo;


import com.project.api.entity.MemberEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberVo {

    private Long memberNo;
    @NotBlank(message = "id를 입력해주세요.")
    private String id;
    @NotBlank(message = "pw를 입력해주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;


    @Builder
    public MemberVo(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }



    public MemberEntity getMemberEntity(){
        MemberEntity member = MemberEntity.builder()
                .id(getId())
                .password(getPassword())
                .name(getName())
                .build();
        return member;
    }
}
