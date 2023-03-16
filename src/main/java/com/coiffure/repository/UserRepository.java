package com.coiffure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.coiffure.model.User;
import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    @Query("SELECT p FROM User p WHERE CONCAT(p.id, ' ', p.firstName, ' ', p.lastName, ' ', p.email, ' ', p.password, ' ', p.mobile,' ', p.role, ' ', p.locked, ' ', p.enabled, ' ', p.createdAt, ' ', p.updatedAt) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);

    Optional<User> findByEmail(String email);
    Optional<User> findByMobile(String mobile);
}
