package com.jpa.tarea_274.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.jpa.tarea_274.Exceptions.*;
import com.jpa.tarea_274.dto.BoatDto;
import com.jpa.tarea_274.models.Boat;
import com.jpa.tarea_274.models.Member;
import com.jpa.tarea_274.repositories.BoatRepository;
import com.jpa.tarea_274.repositories.MemberRepository;
import com.jpa.tarea_274.services.GenericCrudService;

@Service
public class BoatServiceImp implements GenericCrudService<Boat, BoatDto> {

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Boat> getAll() {
        List<Boat> entities = boatRepository.findAll();

        if (entities.isEmpty()) {
            throw new NotFoundException("No hay datos");
        }
        return entities;
    }

    @Override
    public Boat add(BoatDto newEntity) {
        if (newEntity == null) {
            throw new BadRequestException("El boat no debe ser nulo");
        }
        try {
            Member member = memberRepository.findById(newEntity.getMemberId()).orElseThrow(
                    () -> new NotFoundException("No existe nigun member con el ID " + newEntity.getMemberId()));
            Boat boat = Boat.builder()
                    .license(newEntity.getLicense())
                    .name(newEntity.getName())
                    .mooringNumber(newEntity.getMooringNumber())
                    .fee(newEntity.getFee())
                    .member(member)
                    .build();

            return boatRepository.save(boat);
        } catch (DataIntegrityViolationException e) {
            // si hay violacion en la clave única o intentadno insertar un intedad ya
            // existente
            throw new ConflictException("Error añadiendo nuevo boat!" + e.getMessage());
        } catch (Exception e) {
            // Si ocurre cualquier otra excepción no esperada
            throw new ServerErrorException("ERROR Añadiendo nuevo boat!: " + e.getMessage(), e);
        }
    }

    @Override
    public Boat getById(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        Boat entity = boatRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No hay ningun boat con el ID: " + id));
        return entity;
    }

    @Override
    public Boat edit(Long id, BoatDto newEntity) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }

        Boat boatToEdit = boatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay ningun boat con el ID: " + id));

        boatToEdit.setLicense(newEntity.getLicense());
        boatToEdit.setFee(newEntity.getFee());
        boatToEdit.setName(newEntity.getName());
        boatToEdit.setMooringNumber(newEntity.getMooringNumber());

        try {
            Boat boat = boatRepository.save(boatToEdit);
            return boat;
        } catch (Exception e) {
            throw new BadRequestException("No se ha podido actualizar el boat " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        if (boatRepository.existsById(id)) {
            try {
                boatRepository.deleteById(id);
            } catch (IllegalArgumentException ex) {
                throw new ConflictException("No se puede editar el boat con el ID: " + id
                        + " Data integrity violation \n" + ex.getMessage());
            }
        } else {
            throw new NotFoundException("No existe ningun registro con el ID: " + id);
        }
    }

    public List<Boat> getAllByMemberId(Long memberId) {
        if (memberId == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }

        List<Boat> entities = boatRepository.findAllByMemberId(memberId);

        return entities;
    }

}
