package com.jpa.tarea_274.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.jdbc.BatchFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.jpa.tarea_274.Exceptions.*;
import com.jpa.tarea_274.dto.TripDto;
import com.jpa.tarea_274.models.Boat;
import com.jpa.tarea_274.models.Patron;
import com.jpa.tarea_274.models.Trip;
import com.jpa.tarea_274.models.Member;
import com.jpa.tarea_274.repositories.*;
import com.jpa.tarea_274.services.GenericCrudService;

@Service
public class TripServiceImp implements GenericCrudService<Trip, TripDto> {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private BoatRepository boatRepository;

    @Override
    public Trip add(TripDto newEntity) {
        if (newEntity == null) {
            throw new BadRequestException("El trip no debe ser nulo");
        }
        try {
            Patron patron = patronRepository.findById(newEntity.getPatronId()).orElseThrow(
                    () -> new NotFoundException("No existe ninguna parton con el ID: " + newEntity.getPatronId()));

            Boat boat = boatRepository.findById(newEntity.getBoatId()).orElseThrow(
                    () -> new NotFoundException("No existe ninguna boat con el ID: " + newEntity.getBoatId()));

            Trip trip = Trip.builder()
                    .boat(boat)
                    .patron(patron)
                    .fechaTrip(newEntity.getFechaTrip())
                    .destino(newEntity.getDestino())
                    .build();
            return tripRepository.save(trip);
        } catch (DataIntegrityViolationException e) {
            // si hay violacion en la clave única o intentadno insertar un intedad ya
            // existente
            throw new ConflictException("Error añadiendo nuevo trip!" + e.getMessage());
        } catch (Exception e) {
            // Si ocurre cualquier otra excepción no esperada
            throw new ServerErrorException("ERROR Añadiendo nuevo trip!: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Trip> getAll() {
        List<Trip> entities = tripRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("No hay datos");
        }
        return entities;
    }

    @Override
    public Trip getById(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        Trip entity = tripRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No hay ningun trip con el ID: " + id));
        return entity;
    }

    @Override
    public Trip edit(Long id, TripDto newEntity) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }

        Trip tripToEdit = tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay ningun trip con el ID: " + id));

        tripToEdit.setDestino(newEntity.getDestino());
        tripToEdit.setFechaTrip(newEntity.getFechaTrip());

        try {
            Trip trip = tripRepository.save(tripToEdit);
            return trip;
        } catch (Exception e) {
            throw new BadRequestException("No se ha podido actualizar el trip " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        if (tripRepository.existsById(id)) {
            try {
                tripRepository.deleteById(id);
            } catch (IllegalArgumentException ex) {
                throw new ConflictException("No se puede editar el trip con el ID: " + id
                        + " Data integrity violation \n" + ex.getMessage());
            }
        }
    }

}
