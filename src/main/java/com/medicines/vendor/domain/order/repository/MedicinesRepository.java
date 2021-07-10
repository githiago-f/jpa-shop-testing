package com.medicines.vendor.domain.order.repository;

import com.medicines.vendor.domain.order.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinesRepository extends JpaRepository<Medicine, Long> {
	Page<Medicine> findAll(Pageable pageable);
}
