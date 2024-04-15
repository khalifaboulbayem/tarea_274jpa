package com.jpa.tarea_274.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jpa.tarea_274.Exceptions.*;
import com.jpa.tarea_274.dto.responses.UserResponse;
import com.jpa.tarea_274.models.User;
import com.jpa.tarea_274.repositories.UserRepository;
import com.jpa.tarea_274.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public UserResponse getUserByUserName() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			if (username == null) {
				throw new NotFoundException("El usuario " + username + "no existe");
			}
			User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new ForbiddenException("Accese denegado!"));

			return UserResponse.builder()
					.id(user.getId())
					.username(user.getUsername())
					.email(user.getEmail())
					.role(user.getRole())
					.build();

		} else {
			throw new ForbiddenException("Accese denegado!");
		}

	}
	/*
	 * @Override
	 * public User create(User userModel) {
	 * User user = userRepository.findById(userModel.getId());
	 * if (user != null) {
	 * throw new ResponseStatusException(HttpStatus.CONFLICT,
	 * "El usuario con el Id " + user.getId() + "Ya existe");
	 * }
	 * 
	 * if (userModel.getUsername().isBlank()) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	 * "El nick de usuario es requerido");
	 * }
	 * return userRepository.save(userModel);
	 * }
	 * 
	 * @Override
	 * public User getById(Long id) {
	 * if (id == null) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	 * "El Id no debe ser nulo");
	 * }
	 * User user = userRepository.findById(id);
	 * if (user == null) {
	 * throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "El usuario con el id " + id + " no existe");
	 * }
	 * return user;
	 * }
	 * 
	 * @Override
	 * public User edit(Long id, User userRequest) {
	 * if (id == null) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	 * "El Id no debe ser nulo");
	 * }
	 * 
	 * if (userRequest.getUsername().isBlank()) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	 * "El nick de usuario es requerido");
	 * }
	 * 
	 * User user = userRepository.findById(id);
	 * 
	 * if (user == null) {
	 * throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "No hay ningun usuario con el ID:  " + id);
	 * }
	 * userRequest.setId(id);
	 * userRepository.edit(userRequest);
	 * 
	 * return userRequest;
	 * }
	 * 
	 * @Override
	 * public void delete(Long id) {
	 * if (id == null) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	 * "El Id no debe ser nulo");
	 * }
	 * User user = userRepository.findById(id);
	 * if (user == null) {
	 * throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "El usuario con el id " + id + " no existe");
	 * }
	 * userRepository.deleteById(id);
	 * }
	 * 
	 * @Override
	 * public Optional<User> findByNick(String nickname) {
	 * if (nickname == null) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	 * "El Id no debe ser nulo");
	 * }
	 * return userRepository.findByUsername(nickname);
	 * }
	 */

	@Override
	public User create(User userModel) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getById'");
	}

	@Override
	public User edit(Long id, User userModel) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'edit'");
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	@Override
	public Optional<User> findByNick(String nickname) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByNick'");
	}
}
