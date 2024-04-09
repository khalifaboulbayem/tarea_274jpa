package com.jpa.tarea_274.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.jpa.tarea_274.Exceptions.*;
import com.jpa.tarea_274.dto.MemberDto;
import com.jpa.tarea_274.models.Member;
import com.jpa.tarea_274.repositories.MemberRepository;
import com.jpa.tarea_274.services.GenericCrudService;

@Service
public class MemberSeriveImp implements GenericCrudService<Member, MemberDto> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member add(MemberDto newEntity) {
        if (newEntity == null) {
            throw new BadRequestException("El member no debe ser nulo");
        }
        try {
            Member memberToAdd = Member.builder()
                    .name(newEntity.getName())
                    .address(newEntity.getAddress())
                    .phone(newEntity.getPhone())
                    .build();
            return memberRepository.save(memberToAdd);
        } catch (DataIntegrityViolationException e) {
            // si hay violacion en la clave única o intentadno insertar un intedad ya
            // existente
            throw new ConflictException("Error añadiendo nuevo member!" + e.getMessage());
        } catch (Exception e) {
            // Si ocurre cualquier otra excepción no esperada
            throw new ServerErrorException("ERROR Añadiendo nuevo member!: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Member> getAll() {
        List<Member> entities = memberRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("No hay datos");
        }
        return entities;
    }

    @Override
    public Member getById(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        Member entity = memberRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No hay ningun member con el ID: " + id));
        return entity;
    }

    @Override
    public Member edit(Long id, MemberDto newEntity) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }

        Member memberToEdit = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay ningun member con el ID: " + id));

        memberToEdit.setAddress(newEntity.getAddress());
        memberToEdit.setName(newEntity.getName());
        memberToEdit.setPhone(newEntity.getPhone());
        try {
            Member member = memberRepository.save(memberToEdit);
            return member;
        } catch (Exception e) {
            throw new BadRequestException("No se ha podido actualizar el member " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no debe ser nulo!");
        }
        if (memberRepository.existsById(id)) {
            try {
                memberRepository.deleteById(id);
            } catch (IllegalArgumentException ex) {
                throw new ConflictException("No se puede editar el member con el ID: " + id
                        + " Data integrity violation \n" + ex.getMessage());
            }
        } else {
            throw new NotFoundException("No existe ningun registro con el ID: " + id);
        }
    }

}
