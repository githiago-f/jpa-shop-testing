package com.medicines.vendor.domain.laboratory.repository;

import com.medicines.vendor.domain.laboratory.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
}
