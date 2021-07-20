package com.medicines.vendor.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.vo.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data @Builder
@AllArgsConstructor
public class OrderDTO {
	@CNPJ
	@NotEmpty
	@JsonProperty(value = "client_cnpj")
	private String client;
	private List<OrderItemDTO> items;

	public boolean hasItems() {
		return getItems() != null && getItems().size() >= 1;
	}

	public Order toEntity() {
		return Order.builder()
			.state(OrderState.TO_CONFIRM)
			.build();
	}
}
