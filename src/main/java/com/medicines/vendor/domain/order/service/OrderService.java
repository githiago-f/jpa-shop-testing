package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.OrderItem;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.dto.OrderItemDTO;
import com.medicines.vendor.domain.order.repository.OrderRepository;
import com.medicines.vendor.domain.order.service.errors.CannotAddItemsException;
import com.medicines.vendor.domain.order.service.errors.MedicineInactiveException;
import com.medicines.vendor.domain.order.service.errors.NoItemsInOrderException;
import com.medicines.vendor.shared.errors.NotFoundException;
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
		Order order = orderRepository.save(orderDTO.toEntity());
		assert orderDTO.getItems() != null;
		for(OrderItemDTO item : orderDTO.getItems()) {
			Medicine medicine = medicinesRepository.findByCode(item.getMedicineCode())
				.orElseThrow(() -> new NotFoundException("Medicine not found!"));
			order = addItemToOrder(order, medicine, item.getQuantity());
		}
		return order;
	}

	public Order addItemToOrder(Order order, Medicine medicine, Integer quantity) {
		if(!order.canAddItems()) {
			throw new CannotAddItemsException();
		}
		if (!medicine.isActive()) {
			throw new MedicineInactiveException();
		}
		OrderItem item = OrderItem.builder()
			.order(order)
			.medicine(medicine)
			.quantity(quantity)
			.unitPrice(medicine.getPrice())
			.build();

		order.addItem(item);
		return orderRepository.save(order);
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
