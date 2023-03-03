package com.project.api.service;

import com.project.api.entity.MemberEntity;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity createMember(MemberVo memberVo) {
        Optional<MemberEntity> memberOptional = memberRepository.findById(memberVo.getId());

        if (memberOptional.isPresent()) {
            throw new InvalidRequest("id", "이미 가입된 ID입니다.");
        }

        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
                16,
                8,
                1,
                32,
                64);

        String encryptedPassword = encoder.encode(memberVo.getPassword());

        MemberEntity member = MemberEntity.builder()
                .id(memberVo.getId())
                .password(encryptedPassword)
                .name(memberVo.getPassword())
                .build();

        return memberRepository.save(member);
    }

    public Optional<MemberEntity> findById(MemberVo memberVo) {
        return memberRepository.findById(memberVo.getId());
    }
    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }

}
