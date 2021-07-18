package com.medicines.vendor.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.vo.OrderState;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderDTO {
	@CNPJ
	@NotEmpty
	@JsonProperty(value = "client_cnpj")
	private String client;

	@Nullable
	private List<OrderItemDTO> items;

	private Order toEntity() {
		return Order.builder()
			.state(OrderState.TO_CONFIRM)
			.build();
	}
}
