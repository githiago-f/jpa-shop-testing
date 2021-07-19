package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.OrderItem;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.dto.OrderItemDTO;
import com.medicines.vendor.domain.order.repository.OrderRepository;
import com.medicines.vendor.domain.order.service.errors.CannotAddItemsException;
import com.medicines.vendor.domain.order.service.errors.MedicineInactiveException;
import com.medicines.vendor.domain.order.service.errors.NoItemsInOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order openOrder(OrderDTO orderDTO) {
		if(!orderDTO.hasItems()) {
			throw new NoItemsInOrderException();
		}
		Order order = orderRepository.save(orderDTO.toEntity());
		assert orderDTO.getItems() != null;
		for(OrderItemDTO item : orderDTO.getItems()) {
			// TODO: insert order items and return
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
