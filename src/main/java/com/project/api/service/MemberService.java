package com.project.api.service;

import com.project.api.crypto.PasswordEncoder;
import com.project.api.entity.MemberEntity;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity createMember(MemberVo memberVo) {
        MemberEntity memberEntity = memberRepository.findById(memberVo.getId());

        if (memberEntity == null) {
            throw new InvalidRequest("id", "이미 가입된 ID입니다.");
        }

        PasswordEncoder encoder = new PasswordEncoder();

        String encryptedPassword = encoder.encrpyt(memberVo.getPassword());

        MemberEntity member = MemberEntity.builder()
                .id(memberVo.getId())
                .password(encryptedPassword)
                .name(memberVo.getPassword())
                .build();

        return memberRepository.save(member);
    }

//    @Transactional
//    public String signin(MemberVo memberVo) {
//        MemberEntity memberEntity = memberRepository.findById(memberVo.getId())
//                .orElseThrow(InvalidRequest::new);
//
//        PasswordEncoder encoder = new PasswordEncoder();
//
//        var matches  = encoder.matches(memberVo.getPassword(), memberEntity.getPassword());
//
//        if (!matches) {
//            throw new InvalidRequest("login Fail", "로그인 실패");
//        }
//
//        return memberEntity.getId();
//    }

    public MemberEntity findById(MemberVo memberVo) {
        return memberRepository.findById(memberVo.getId());
    }
    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }

}
