package com.medicines.vendor.domain.medicine.repository;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineModel;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface MedicinesRepository extends JpaRepository<Medicine, String>,
	JpaSpecificationExecutor<MedicineSpecification> {

	Optional<Medicine> findByCode(String code);

	@Query(
		value = "SELECT " +
			"new com.medicines.vendor.domain.medicine.dto.MedicineModel(m.code, m.name, m.state, m.createdAt) " +
			"FROM Medicine m WHERE m.state = :medicineState",
		countQuery = "SELECT COUNT(m) FROM Medicine m WHERE m.state = :medicineState")
	Page<MedicineModel> findAllActiveListableMedicines(MedicineState medicineState, Pageable pageable);

	@Query(
		value = "SELECT " +
			"new com.medicines.vendor.domain.medicine.dto.MedicineModel(m.code, m.name, m.state, m.createdAt) " +
			"FROM Medicine m",
		countQuery = "SELECT COUNT(m) FROM Medicine m"
	)
	Page<MedicineModel> findAllListableMedicines(Specification<Medicine> spec, Pageable pageable);

	@Query("SELECT m.price FROM Medicine m WHERE m.code = ?1 AND m.state = 'ACTIVE'")
	Optional<BigDecimal> findActiveMedicinePriceByCode(String code);
}
