package com.medicines.vendor.domain.users.repository;

import com.medicines.vendor.application.security.ApplicationUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUserDetails, Long> {
	Optional<ApplicationUserDetails> findByUsername(String email);
}
