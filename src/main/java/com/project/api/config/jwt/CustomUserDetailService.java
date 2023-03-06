package com.project.api.config.jwt;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByName(username);

        if (memberEntity == null) {
            throw new UsernameNotFoundException("사용자 이름 조회 불가능");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        AccountContext accountContext = new AccountContext(memberEntity, roles);

        return accountContext;
    }



}
