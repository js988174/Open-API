package com.project.api.repository;

import com.project.api.entity.MemberEntity;
import com.project.api.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


}
