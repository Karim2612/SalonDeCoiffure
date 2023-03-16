package com.coiffure.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coiffure.model.User;
import com.coiffure.repository.UserRepository;

@Service
public class UserServises {

	@Autowired
	UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public List<User> getUserInfos() {
		return repository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return repository.findById(id);
	}

	public boolean checkExistedUser(Long id) {
		if (repository.existsById((Long) id)) {
			return true;
		}
		return false;
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public void deleteUserById(Long id) {
		repository.deleteById(id);
	}
}
