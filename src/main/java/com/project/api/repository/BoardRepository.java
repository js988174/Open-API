package com.project.api.repository;

import com.project.api.entity.BoardEntity;
import com.project.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository  extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findByMemberNo(Long no);

}
