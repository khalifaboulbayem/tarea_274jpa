package com.jpa.tarea_274.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.tarea_274.dto.BoatDto;
import com.jpa.tarea_274.models.Boat;
import com.jpa.tarea_274.services.impl.BoatServiceImp;

@RestController
@RequestMapping("/api/boats")
public class BoatController {

    @Autowired
    BoatServiceImp service;

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
     * Devoler un boat segun su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<Boat> details(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    // Obtener boats de un member
    @GetMapping("/member/{id}")
    public ResponseEntity<?> getBoatsByMember(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllByMemberId(id));

    }

    /**
     * Añadir nuevo
     * 
     * @param entity
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BoatDto entity) {
        Boat boatCreated = service.add(entity);
        return ResponseEntity.ok(boatCreated);
    }

    /**
     * Editar el boat
     * 
     * @param id
     * @param entity
     * @return
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Boat> update(@PathVariable Long id, @RequestBody BoatDto entity) {
        Boat boat = service.edit(id, entity);
        return ResponseEntity.ok(boat);
    }

    /**
     * Borrar un boat
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("¡Boat borrado exitosamente!");
    }
}
