package com.medicines.vendor.domain.order.dto;

import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.vo.OrderState;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {
	@CNPJ
	private String client; // this will come from session
	private List<OrderItemDTO> items; // this can be null

	private void toEntity() {
		Order.builder()
			.state(OrderState.TO_CONFIRM)
			.build();
	}
}
