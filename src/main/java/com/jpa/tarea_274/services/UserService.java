package com.jpa.tarea_274.services;

import java.util.List;
import java.util.Optional;

import com.jpa.tarea_274.models.User;

public interface UserService {

	List<User> getAll();

	User create(User userModel);

	User getById(Long id);

	User edit(Long id, User userModel);

	void delete(Long id);

	Optional<User> findByNick(String nickname);
}
