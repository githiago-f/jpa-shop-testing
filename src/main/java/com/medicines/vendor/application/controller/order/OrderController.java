package com.medicines.vendor.application.controller.order;

import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	Order getOrders(@RequestBody OrderDTO orderDTO) {
		Order order = orderService.openOrder(orderDTO);
//		for (OrderItemDTO orderItemDto : orderDTO.getItems()) {
//			Medicine medicine = medicinesRepository.findByCode(orderItemDto.getMedicineCode())
//				.orElseThrow(() -> new NotFoundException("Medicine not found!"));
//			order = orderService.addItemToOrder(order, medicine, orderItemDto.getQuantity());
//		}
		return order;
	}
}
