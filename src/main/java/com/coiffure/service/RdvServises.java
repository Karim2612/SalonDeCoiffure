package com.coiffure.service;

import com.coiffure.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.coiffure.model.Rdv;

@Service
public class RdvServises {

	@Autowired RdvRepository repository;
	@Autowired
	private UserServises userServises;

	public UserServises getUserServises() {
		return userServises;
	}
	public void setUserServises(UserServises userServises) {
		this.userServises = userServises;
	}


	public Rdv saveRdv(Rdv rdv) {
		return repository.save(rdv);
	}

	public List<Rdv> getRdvInfos() {
		return repository.findAll();
	}

	public Optional<Rdv> getRdvById(Long id) {
		return repository.findById(id);
	}

	public boolean checkExistedRdv(Long id) {
		if (repository.existsById((Long) id)) {
			return true;
		}
		return false;
	}

	public Rdv updateRdv(Rdv rdv) {
		return repository.save(rdv);
	}

	public void deleteRdvById(Long id) {
		repository.deleteById(id);
	}
}