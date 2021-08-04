package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.dto.OrderItemDTO;
import com.medicines.vendor.domain.order.repository.OrderRepository;
import com.medicines.vendor.domain.order.service.errors.NoItemsInOrderException;
import com.medicines.vendor.domain.shared.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final MedicinesRepository medicinesRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, MedicinesRepository medicinesRepository) {
		this.orderRepository = orderRepository;
		this.medicinesRepository = medicinesRepository;
	}

	public Order openOrder(OrderDTO orderDTO) {
		if(!orderDTO.hasItems()) {
			throw new NoItemsInOrderException();
		}
		Order order = orderDTO.toEntity();
		for (OrderItemDTO item : orderDTO.getItems()) {
			Medicine medicine = medicinesRepository.findByCode(item.getMedicineCode())
				.orElseThrow(() -> new NotFoundException("Medicine not found!"));
			order.addItem(item.toEntity(order, medicine));
		}
		return orderRepository.save(order);
	}

	public Order confirmOrder(Long id) {
		Order order = orderRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Order not found!"));
		order.confirm();
		return order;
	}
}
