package com.jpa.tarea_274.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.tarea_274.dto.MemberDto;
import com.jpa.tarea_274.models.Member;
import com.jpa.tarea_274.services.impl.MemberSeriveImp;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    MemberSeriveImp service;

    /**
     * Devolver una lista
     * 
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());

    }

    /**
     * Devoler un member segun su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<Member> details(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    /**
     * Añadir nuevo
     * 
     * @param entity
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody MemberDto entity) {
        Member memberCreated = service.add(entity);
        return ResponseEntity.ok(memberCreated);
    }

    /**
     * Editar el member
     * 
     * @param id
     * @param entity
     * @return
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Member> update(@PathVariable long id, @RequestBody MemberDto entity) {
        Member member = service.edit(id, entity);
        return ResponseEntity.ok(member);
    }

    /**
     * Borrar un member
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Member borrado exitosamente!");
    }
}