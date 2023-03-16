package com.coiffure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coiffure.model.Servise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


//@Repository
public interface ServiseRepository extends JpaRepository<Servise, Long>{
	@Query("SELECT p FROM Servise p WHERE CONCAT(p.id, ' ', p.nom_servise, ' ', p.prix_servise, ' ', p.description_servise, ' ', p.date_creation_servise) LIKE %?1%")
	public Page<Servise> findAll(String keyword, Pageable pageable);
	//List<Servise> findByRdvId(Long rdvId);
	//Optional<Servise> findByIdAndRdvId(Long id, Long rdvId);
}