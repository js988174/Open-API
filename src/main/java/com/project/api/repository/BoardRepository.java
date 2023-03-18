package com.project.api.repository;

import com.project.api.entity.Board;
import com.project.api.entity.Member;
import com.project.api.vo.BoardListVo;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface BoardRepository  extends JpaRepository<Board, Long> {
    List<Board> findByMember(Member member);

    @Query(value = "select b from Board b" )
    @BatchSize(size = 3)
    Page<Board> findAll(Pageable pageable);

}
