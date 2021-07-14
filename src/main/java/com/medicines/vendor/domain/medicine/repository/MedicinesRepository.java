package com.medicines.vendor.domain.medicine.repository;

import com.medicines.vendor.domain.medicine.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicinesRepository extends JpaRepository<Medicine, String> {
	Page<Medicine> findAll(Pageable pageable);
}
