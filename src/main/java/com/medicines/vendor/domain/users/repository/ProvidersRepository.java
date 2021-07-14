package com.medicines.vendor.domain.users.repository;

import com.medicines.vendor.domain.users.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidersRepository extends JpaRepository<Provider, Long> {
}
