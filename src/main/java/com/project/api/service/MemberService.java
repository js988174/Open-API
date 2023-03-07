package com.project.api.service;

import com.project.api.crypto.PasswordEncoderCustom;
import com.project.api.entity.MemberEntity;
import com.project.api.exception.InvalidRequest;
import com.project.api.repository.MemberRepository;
import com.project.api.vo.MemberVo;
import com.project.api.vo.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService  {

    private final MemberRepository memberRepository;
    private final PasswordEncoderCustom passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByName(username);

        if (memberEntity == null) {
            throw new UsernameNotFoundException("사용자 이름 조회 불가능");
        }

        UserDetails userDetailDTO = new UserDetailDTO(memberEntity);

        return userDetailDTO;
    }

    public MemberEntity createMember(MemberVo memberVo) {
        MemberEntity memberEntity = memberRepository.findById(memberVo.getId());

        if (memberEntity != null) {
            throw new InvalidRequest("id", "이미 가입된 ID입니다.");
        }

        String encPass = passwordEncoder.encode(memberVo.getPassword());

        MemberEntity member = MemberEntity.builder()
                .id(memberVo.getId())
                .password(encPass)
                .name(memberVo.getName())
                .build();

        return memberRepository.save(member);
    }

    public String signin(MemberVo memberVo) {
        MemberEntity memberEntity = memberRepository.findById(memberVo.getId());
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
