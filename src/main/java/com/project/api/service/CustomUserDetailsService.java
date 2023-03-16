package com.project.api.service;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.Member;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findById(username);

//        if (member == null) {
//            throw new UsernameNotFoundException("사용자 id 조회 불가능");
//        }




        UserDetails userDetailDTO = new UserDetailDTO(member);

        return userDetailDTO;
    }
}