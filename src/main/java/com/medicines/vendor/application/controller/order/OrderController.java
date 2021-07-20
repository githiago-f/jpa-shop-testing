package com.medicines.vendor.application.controller.order;

import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Order getOrders(@RequestBody @Valid OrderDTO orderDTO, Errors errors) {
		return orderService.openOrder(orderDTO);
	}

	@PatchMapping("/confirm/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	void confirmOrder(@PathVariable("id") Long id) {
		orderService.confirmOrder(id);
	}
}
