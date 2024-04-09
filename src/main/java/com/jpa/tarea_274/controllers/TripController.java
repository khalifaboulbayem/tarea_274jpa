package com.jpa.tarea_274.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.tarea_274.dto.TripDto;
import com.jpa.tarea_274.models.Trip;
import com.jpa.tarea_274.services.impl.TripServiceImp;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    TripServiceImp service;

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
     * Devoler un trip segun su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<Trip> details(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    /**
     * Añadir nuevo
     * 
     * @param entity
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TripDto entity) {
        Trip tripCreated = service.add(entity);
        return ResponseEntity.ok(tripCreated);
    }

    /**
     * Editar el trip
     * 
     * @param id
     * @param entity
     * @return
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Trip> update(@PathVariable Long id, @RequestBody TripDto entity) {
        Trip trip = service.edit(id, entity);
        return ResponseEntity.ok(trip);
    }

    /**
     * Borrar un trip
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Trip borrado exitosamente!");
    }
}