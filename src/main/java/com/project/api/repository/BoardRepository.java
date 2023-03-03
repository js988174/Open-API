package com.project.api.repository;

import com.project.api.entity.BoardEntity;
import com.project.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByMember(MemberEntity member);


}
