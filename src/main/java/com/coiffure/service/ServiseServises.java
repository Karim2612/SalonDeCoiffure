package com.coiffure.service;

import com.coiffure.repository.ServiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.coiffure.model.Servise;

@Service
public class ServiseServises {

	@Autowired ServiseRepository repository;
	@Autowired private RdvServises rdvServises;

	public RdvServises getRdvServises() { return rdvServises; }
	public void setRdvServises(RdvServises rdvServises) { this.rdvServises = rdvServises; }


	public Servise saveServise(Servise servise) {
		return repository.save(servise);
	}

	public List<Servise> getServiseInfos() {
		return repository.findAll();
	}

	public Optional<Servise> getServiseById(Long id) {
		return repository.findById(id);
	}

	public boolean checkExistedServise(Long id) {
		if (repository.existsById((Long) id)) {
			return true;
		}
		return false;
	}

	public Servise updateServise(Servise servise) {
		return repository.save(servise);
	}

	public void deleteServiseById(Long id) {
		repository.deleteById(id);
	}
}