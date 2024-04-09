package com.jpa.tarea_274.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.tarea_274.models.Boat;
import java.util.List;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

    List<Boat> findAllByMemberId(Long socioId);

}
