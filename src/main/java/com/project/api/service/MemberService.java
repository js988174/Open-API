package com.project.api.service;

import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.MemberEntity;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public MemberEntity createMember(MemberVo memberVo) {
        MemberEntity memberEntity = memberRepository.findById(memberVo.getId());

        if (memberEntity != null) {
            throw new InvalidRequest("id", "이미 가입된 ID입니다.");
        }

        PasswordEncoderCustom encoder = new PasswordEncoderCustom();

        String encryptedPassword = encoder.encrpyt(memberVo.getPassword());

        MemberEntity member = MemberEntity.builder()
                .id(memberVo.getId())
                .password(encryptedPassword)
                .name(memberVo.getName())
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public String signin(MemberVo memberVo) {
        MemberEntity memberEntity = memberRepository.findById(memberVo.getId());

        PasswordEncoderCustom encoder = new PasswordEncoderCustom();

        var matches  = passwordEncoder.matches(memberVo.getPassword(), memberEntity.getPassword());

        if (!matches) {
            throw new InvalidRequest("login Fail", "로그인 실패");
        }

        return memberEntity.getId();
    }

    public MemberEntity findById(MemberVo memberVo) {
        return memberRepository.findById(memberVo.getId());
    }
    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }

}
