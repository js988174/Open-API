package com.project.api.config.jwt;


import com.project.api.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {

    private final Member member;

    public AccountContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getName(), member.getPassword(), authorities);
        this.member = member;
    }

    public Member getMemberEntity() {
        return member;
    }
}
