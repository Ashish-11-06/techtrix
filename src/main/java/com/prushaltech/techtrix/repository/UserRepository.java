package com.prushaltech.techtrix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prushaltech.techtrix.entity.User;
import com.prushaltech.techtrix.entity.User.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	List<User> findAllByRole(Role role);
}
