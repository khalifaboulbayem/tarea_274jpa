package com.jpa.tarea_274.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.jpa.tarea_274.Exceptions.BadRequestException;
import com.jpa.tarea_274.Exceptions.NotFoundException;
import com.jpa.tarea_274.models.Socio;
import com.jpa.tarea_274.repositories.SocioRepository;
import com.jpa.tarea_274.services.GenericCrudService;

public class SocioSeriveImp implements GenericCrudService<Socio> {

    @Autowired
    private SocioRepository socioRepository;

    @Override
    public Socio add(Socio newEntity) {
        if (newEntity == null) {
            throw new BadRequestException("El socio no debe ser nulo");
        }
        try {
            return socioRepository.save(newEntity);
        } catch (DataIntegrityViolationException e) {
            // si hay violacion en la clave única o intentadno insertar un intedad ya
            // existente
            throw new ConflictException("Error añadiendo nuevo socio!" + e.getMessage());
        } catch (Exception e) {
            // Si ocurre cualquier otra excepción no esperada
            throw new ServerErrorException("Error adding entity: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Socio> getAll() {
        List<Socio> entities = socioRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("No hay datos");
        }
        return entities;
    }

    @Override
    public Socio getById(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        Socio entity = socioRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No hay ningun socio con el ID: " + id));
        return entity;
    }

    @Override
    public Socio edit(Long id, SocioDto newEntity) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }

        Socio socioToEdit = socioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay ningun socio con el ID: " + id));

        // usar builder para costruir el objeto Socio

        try {
            Socio socio = socioRepository.save(socioToEdit);
            return socio;
        } catch (Exception e) {
            throw new BadRequestException("No se ha podido actualizar el socio ", e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        if (socioRepository.existsById(id)) {
            try {
                socioRepository.deleteById(id);
            } catch (IllegalArgumentException ex) {
                throw new ConflictException("No se puede editar el socio con el ID: " + id
                        + " Data integrity violation \n" + ex.getMessage());
            }
        }
    }

}
