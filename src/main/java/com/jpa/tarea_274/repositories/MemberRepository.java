package com.jpa.tarea_274.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.tarea_274.models.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
