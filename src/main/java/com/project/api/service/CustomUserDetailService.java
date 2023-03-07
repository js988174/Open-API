package com.project.api.service;

import com.project.api.config.jwt.AccountContext;
import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.entity.MemberEntity;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByName(username);

        if (memberEntity == null) {
            throw new UsernameNotFoundException("사용자 이름 조회 불가능");
        }

        UserDetails userDetailDTO = new UserDetailDTO(memberEntity);


        return userDetailDTO;
    }


}
