package com.medicines.vendor.domain.medicine.repository;

import com.medicines.vendor.domain.medicine.Medicine;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class MedicineSpecification {
	public static Specification<Medicine> isFromLaboratory(UUID labId) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("laboratory.id"), labId);
	}
}
