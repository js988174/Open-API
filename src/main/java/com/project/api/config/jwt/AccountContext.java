package com.project.api.config.jwt;


import com.project.api.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {

    private final MemberEntity memberEntity;

    public AccountContext(MemberEntity memberEntity, Collection<? extends GrantedAuthority> authorities) {
        super(memberEntity.getName(), memberEntity.getPassword(), authorities);
        this.memberEntity = memberEntity;
    }

    public MemberEntity getMemberEntity() {
        return memberEntity;
    }
}
