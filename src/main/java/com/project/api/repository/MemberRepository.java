package com.project.api.repository;

import com.project.api.entity.MemberEntity;
import com.project.api.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByName(String name);


    //Optional<MemberEntity> findById(String id);

    MemberEntity findById(String id);
}
