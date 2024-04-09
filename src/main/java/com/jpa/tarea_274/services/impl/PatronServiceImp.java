package com.jpa.tarea_274.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.jpa.tarea_274.Exceptions.*;
import com.jpa.tarea_274.dto.PatronDto;
import com.jpa.tarea_274.models.Patron;
import com.jpa.tarea_274.repositories.PatronRepository;
import com.jpa.tarea_274.services.GenericCrudService;

@Service
public class PatronServiceImp implements GenericCrudService<Patron, PatronDto> {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public Patron add(PatronDto newEntity) {
        if (newEntity == null) {
            throw new BadRequestException("El patron no debe ser nulo");
        }
        try {
            Patron patron = Patron.builder()
                    .name(newEntity.getName())
                    .phone(newEntity.getPhone())
                    .build();
            return patronRepository.save(patron);
        } catch (DataIntegrityViolationException e) {
            // si hay violacion en la clave única o intentadno insertar un intedad ya
            // existente
            throw new ConflictException("Error añadiendo nuevo patron!" + e.getMessage());
        } catch (Exception e) {
            // Si ocurre cualquier otra excepción no esperada
            throw new ServerErrorException("ERROR Añadiendo nuevo patron!: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Patron> getAll() {
        List<Patron> entities = patronRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("No hay datos");
        }
        return entities;
    }

    @Override
    public Patron getById(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        Patron entity = patronRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No hay ningun patron con el ID: " + id));
        return entity;
    }

    @Override
    public Patron edit(Long id, PatronDto newEntity) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }

        Patron patronToEdit = patronRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay ningun patron con el ID: " + id));

        patronToEdit.setName(newEntity.getName());
        patronToEdit.setPhone(newEntity.getPhone());

        try {
            Patron patron = patronRepository.save(patronToEdit);
            return patron;
        } catch (Exception e) {
            throw new BadRequestException("No se ha podido actualizar el patron " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        if (patronRepository.existsById(id)) {
            try {
                patronRepository.deleteById(id);
            } catch (IllegalArgumentException ex) {
                throw new ConflictException("No se puede editar el patron con el ID: " + id
                        + " Data integrity violation \n" + ex.getMessage());
            }
        } else {
            throw new NotFoundException("No existe ningun registro con el ID: " + id);
        }
    }

}
