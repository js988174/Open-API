package com.project.api.service;

import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.Member;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("MemberService")
@RequiredArgsConstructor
public class MemberService implements UserDetailsService  {

    private final MemberRepository memberRepository;
    private final PasswordEncoderCustom passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username);

        if (member == null) {
            throw new UsernameNotFoundException("사용자 id 조회 불가능");
        }


        UserDetails userDetailDTO = new UserDetailDTO(member);

        return userDetailDTO;
    }

    public Member createMember(MemberVo memberVo) {
        Member memberEntity = memberRepository.findById(memberVo.getId());

        if (memberEntity != null) {
            throw new InvalidRequest("id", "이미 가입된 ID입니다.");
        }

        String encPass = passwordEncoder.encode(memberVo.getPassword());

        Member member = Member.builder()
                .id(memberVo.getId())
                .password(encPass)
                .name(memberVo.getName())
                .build();

        return memberRepository.save(member);
    }

    public String signin(MemberVo memberVo) {
        Member member = memberRepository.findById(memberVo.getId());
        var matches  = passwordEncoder.matches(memberVo.getPassword(), member.getPassword());
        if (!matches) {
            throw new InvalidRequest("login Fail", "로그인 실패");
        }

        return member.getId();
    }

    public Member findById(MemberVo memberVo) {
        return memberRepository.findById(memberVo.getId());
    }
    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }


}
