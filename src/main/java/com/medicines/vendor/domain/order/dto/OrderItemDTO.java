package com.medicines.vendor.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.OrderItem;
import com.medicines.vendor.domain.order.service.errors.MedicineInactiveException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class OrderItemDTO {
	@JsonProperty("medicine_code")
	private String medicineCode;
	private Integer quantity;

	public OrderItem toEntity(Order order, Medicine medicine) {
		if(!medicine.isActive()) {
			throw new MedicineInactiveException();
		}
		return OrderItem.builder()
			.order(order)
			.quantity(quantity)
			.unitPrice(medicine.getPrice())
			.medicine(medicine)
			.build();
	}
}
