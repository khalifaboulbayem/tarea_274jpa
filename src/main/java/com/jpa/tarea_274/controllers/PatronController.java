package com.jpa.tarea_274.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.tarea_274.dto.PatronDto;
import com.jpa.tarea_274.models.Patron;
import com.jpa.tarea_274.services.impl.PatronServiceImp;

import java.util.Map;

@RestController
@RequestMapping("/api/patrones")
public class PatronController {

    @Autowired
    PatronServiceImp service;

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
     * Devoler un patron segun su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patron> details(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    /**
     * Añadir nuevo
     * 
     * @param entity
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody PatronDto entity) {
        Patron patronCreated = service.add(entity);
        return ResponseEntity.ok(patronCreated);
    }

    /**
     * Editar el patron
     * 
     * @param id
     * @param entity
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patron> update(@PathVariable long id, @RequestBody PatronDto entity) {
        Patron patron = service.edit(id, entity);
        return ResponseEntity.ok(patron);
    }

    /**
     * Borrar un patron
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Patron borrado exitosamente!"));

    }
}