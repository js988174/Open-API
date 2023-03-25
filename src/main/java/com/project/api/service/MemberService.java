package com.project.api.service;

import com.project.api.config.jwt.JwtTokenProvider;
import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.Member;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.response.BoardListResult;
import com.project.api.response.MemberListResult;
import com.project.api.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("MemberService")
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoderCustom passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

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
        UserDetailDTO userDetails = (UserDetailDTO)userDetailsService.loadUserByUsername(memberVo.getId());
        var matches  = passwordEncoder.matches(memberVo.getPassword(), userDetails.getPassword());
        if (!matches) {
            throw new InvalidRequest("login Fail", "로그인 실패");
        };
        String token =
                jwtTokenProvider.createToken(userDetails.getUsername(), userDetails.getRoles());
        return token;
    }

    public Member findById(MemberVo memberVo) {
        return memberRepository.findById(memberVo.getId());
    }
    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }

    public MemberListResult findAllList(Pageable pageable) {
        Page<Member> memberList = memberRepository.findAll(pageable);
        MemberListResult<Integer, List<MemberListVo>> resultList = new MemberListResult<>(memberList.getTotalPages(),
                memberList.toList().stream()
                        .map(MemberListVo::new)
                        .collect(Collectors.toList()));

        return resultList;
    }


}
