package com.project.api.repository;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardRepository  extends JpaRepository<Board, Long> {
    List<Board> findByMember(Member member);


}
