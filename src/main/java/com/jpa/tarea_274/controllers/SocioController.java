package com.jpa.tarea_274.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.tarea_274.dto.SocioDto;
import com.jpa.tarea_274.models.Socio;
import com.jpa.tarea_274.services.impl.SocioSeriveImp;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    SocioSeriveImp service;

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
     * Devoler un socio segun su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<Socio> details(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    /**
     * Añadir nuevo
     * 
     * @param entity
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SocioDto entity) {
        Socio socioCreated = service.add(entity);
        return ResponseEntity.ok(socioCreated);
    }

    /**
     * Editar el socio
     * 
     * @param id
     * @param entity
     * @return
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Socio> update(@PathVariable long id, @RequestBody SocioDto entity) {
        Socio socio = service.edit(id, entity);
        return ResponseEntity.ok(socio);
    }

    /**
     * Borrar un socio
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}