package com.medicines.vendor.domain.medicine.repository;

import com.medicines.vendor.domain.medicine.Datasheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasheetRepository extends JpaRepository<Datasheet, Long> {
}
