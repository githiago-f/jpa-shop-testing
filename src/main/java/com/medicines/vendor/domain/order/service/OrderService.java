package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.dto.OrderItemDTO;
import com.medicines.vendor.domain.order.repository.OrderItemRepository;
import com.medicines.vendor.domain.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
	}

	void openOrder(OrderDTO orderDTO, Medicine medicine) {
		/**
		 * rules:
		 * if medicine is active
		 * and at last one item was selected
		 *
		 * creates a order on database
		 */
	}

	void addItemToOrder(OrderItemDTO orderItemDTO) {
		orderItemRepository.save(orderItemDTO.toEntity());
	}

	void confirmOrder() {
		// TODO: confirm order logic
	}

	void dispatchOrder() {
		// TODO: dispatch order
	}

	void cancelOrder() {
		// TODO: cancel order logic
	}
}
