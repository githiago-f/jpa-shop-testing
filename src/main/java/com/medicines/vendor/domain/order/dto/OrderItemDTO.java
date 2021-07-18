package com.medicines.vendor.domain.order.dto;

import com.medicines.vendor.domain.order.OrderItem;

public class OrderItemDTO {
	public OrderItem toEntity() {
		return OrderItem.builder().build();
	}
}
