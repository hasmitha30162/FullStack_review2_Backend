package com.klu.ArtifyBackend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.ArtifyBackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByRole(String role);

    Optional<User> findByEmailAndRole(String email, String role);

    boolean existsByEmailAndRole(String email, String role);
}
