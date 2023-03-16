package com.coiffure.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coiffure.model.Rdv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

//@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long>{
	@Query("SELECT p FROM Rdv p WHERE CONCAT(p.id, ' ', p.date_heur_rdv, ' ', p.description_rdv, ' ', p.date_creation_rdv) LIKE %?1%")
	public Page<Rdv> findAll(String keyword, Pageable pageable);

	List<Rdv> findByUserId(Long userId);
	 Optional<Rdv> findByIdAndUserId(Long id, Long userId);
}