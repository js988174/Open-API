package com.project.api.repository;

import com.project.api.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
    Member findById(String id);
    Page<Member> findAll(Pageable pageable);
}
