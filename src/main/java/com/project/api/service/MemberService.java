package com.project.api.service;

import com.project.api.entity.MemberEntity;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity createMember(MemberVo memberVo) {

        MemberEntity member = MemberEntity.builder()
                .memberVo(memberVo)
                .build();

        return memberRepository.save(member);
    }

    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }

}
