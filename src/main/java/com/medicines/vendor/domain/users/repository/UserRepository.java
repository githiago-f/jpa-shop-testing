package com.medicines.vendor.domain.users.repository;

import com.medicines.vendor.application.security.ApplicationUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUserDetails, UUID> {
	Optional<ApplicationUserDetails> findByUsername(String email);
}
