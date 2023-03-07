package com.project.api.service;

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
public class SignService {

    private final MemberRepository memberRepository;




    public String signin(MemberVo memberVo) {
        MemberEntity memberEntity = memberRepository.findById(memberVo.getId());


        return memberEntity.getId();
    }

    public MemberEntity findById(MemberVo memberVo) {
        return memberRepository.findById(memberVo.getId());
    }
    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }
}
