package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.OrderItem;
import com.medicines.vendor.domain.order.dto.OrderDTO;
import com.medicines.vendor.domain.order.dto.OrderItemDTO;
import com.medicines.vendor.domain.order.repository.OrderRepository;
import com.medicines.vendor.domain.order.service.errors.CannotAddItemsException;
import com.medicines.vendor.domain.order.service.errors.CannotConfirmOrderException;
import com.medicines.vendor.domain.order.service.errors.MedicineInactiveException;
import com.medicines.vendor.domain.order.service.errors.NoItemsInOrderException;
import com.medicines.vendor.domain.order.vo.OrderState;
import com.medicines.vendor.shared.errors.NotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("# OrderService")
class OrderServiceTest {
	@Mock private MedicinesRepository medicinesRepository;
	@Mock private OrderRepository orderRepository;
	@InjectMocks private OrderService orderService;

	protected Medicine medicineActive, medicineInactive;

	@BeforeEach
	void setUp() {
		medicineActive = Medicine.builder()
			.state(MedicineState.ACTIVE)
			.price(new BigDecimal(5))
			.build();
		medicineInactive = Medicine.builder()
			.state(MedicineState.DATASHEET_REQUIRED)
			.price(new BigDecimal(5))
			.build();
	}

	@Nested
	@DisplayName(".openOrder")
	class OpenOrder {
		OrderItem item;

		@BeforeEach
		void setUp() {
			Order builtOrder = Order.builder()
				.state(OrderState.TO_CONFIRM)
				.build();

			item = OrderItem.builder()
				.order(builtOrder)
				.quantity(1)
				.medicine(medicineActive)
				.unitPrice(medicineActive.getPrice())
				.build();

			builtOrder.addItem(item);
			builtOrder.calculateTotal();

			when(orderRepository.save(any(Order.class)))
				.thenReturn(builtOrder);
			when(medicinesRepository.findByCode(any(String.class)))
				.thenReturn(Optional.of(medicineActive));
		}

		@Test
		@DisplayName("at last one item is present")
		void atLastOneOrderItemIsPresent() {
			OrderDTO.OrderDTOBuilder dtoBuilder = OrderDTO.builder()
				.client("11.542.936/0001-60");

			OrderDTO finalOrderDTO = dtoBuilder.build();
			Executable executable = () -> orderService.openOrder(finalOrderDTO);
			assertThrows(NoItemsInOrderException.class, executable);

			List<OrderItemDTO> orderItemDTOS = List.of(
				OrderItemDTO.builder()
					.medicineCode("anything")
					.quantity(1)
					.build()
			);

			OrderDTO orderDTO = dtoBuilder.items(orderItemDTOS).build();
			Order order = orderService.openOrder(orderDTO);
			assertEquals(1, order.getItems().size());
			assertEquals(new BigDecimal("5.00"), order.getTotal());
		}
	}

	@Nested
	@DisplayName(".confirmOrder")
	class ConfirmOrder {
		Order confirmedOrder, toConfirmOrder;

		@BeforeEach
		void setUp() {
			confirmedOrder = Order.builder()
				.state(OrderState.CONFIRMED)
				.build();
			toConfirmOrder = Order.builder()
				.state(OrderState.TO_CONFIRM)
				.build();
			when(orderRepository.findById(2L))
				.thenReturn(Optional.of(toConfirmOrder));
			when(orderRepository.findById(1L))
				.thenReturn(Optional.of(confirmedOrder));
		}

		@Test
		@DisplayName("id parameter should return a valid Order")
		void idParameterShouldReturnValidOrder() {
			when(orderRepository.findById(3L))
				.thenReturn(Optional.empty());
			Executable executable = () -> orderService.confirmOrder(3L);
			assertThrows(NotFoundException.class, executable);
		}

		@Test
		@DisplayName("cannot confirm already confirmed order")
		void cannotConfirmAlreadyConfirmedOrder() {
			Executable executable = () -> orderService.confirmOrder(1L);
			assertThrows(CannotConfirmOrderException.class, executable);
		}

		@Test
		@DisplayName("on confirm changes state to confirmed")
		void onConfirmChangeState() {
			Order order = orderService.confirmOrder(2L);
			assertEquals(OrderState.CONFIRMED, order.getState());
		}
	}
}
