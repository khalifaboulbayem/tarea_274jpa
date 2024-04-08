package com.jpa.tarea_274.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.tarea_274.models.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

}
