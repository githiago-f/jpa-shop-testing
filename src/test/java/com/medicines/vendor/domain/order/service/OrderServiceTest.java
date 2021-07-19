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
import com.medicines.vendor.domain.order.service.errors.MedicineInactiveException;
import com.medicines.vendor.domain.order.service.errors.NoItemsInOrderException;
import com.medicines.vendor.domain.order.vo.OrderState;
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
				.medicine(medicineActive)
				.quantity(1)
				.build();

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

			Executable executable = () -> orderService.openOrder(dtoBuilder.build());
			assertThrows(NoItemsInOrderException.class, executable);

			OrderDTO orderDTO = dtoBuilder
				.items(
					List.of(
						OrderItemDTO.builder()
							.medicineCode("anything")
							.quantity(1)
							.build()
					)
				).build();
			Order order = orderService.openOrder(orderDTO);
			assertEquals(1, order.getItems().size());
			assertEquals(new BigDecimal(5), order.getTotal());
		}
	}

	@Nested
	@DisplayName(".addItemToOrder")
	class AddItemToOrder {
		Order order;
		OrderItem item;

		@BeforeEach
		void setUp() {
			order = Order.builder()
					.state(OrderState.TO_CONFIRM)
					.build();

			item = OrderItem.builder()
				.order(order)
				.medicine(medicineActive)
				.quantity(1)
				.build();

			Order builtOrder = Order.builder().build();
			builtOrder.addItem(item);

			when(orderRepository.save(order)).thenReturn(builtOrder);
		}

		@Test
		@DisplayName("medicine has to be active")
		void medicineHasToBeActive() {
			Order orderWithItem = orderService.addItemToOrder(order, medicineActive, 1);
			Executable throwsError = () -> orderService.addItemToOrder(order, medicineInactive, 2);
			assertEquals(1, orderWithItem.getItems().size());
			assertThrows(MedicineInactiveException.class, throwsError);
		}

		@Test
		@DisplayName("Order has to be on confirmation stage")
		void orderHasToBeOnConfirmationStage() {
			Order orderConfirmed = Order.builder().state(OrderState.CONFIRMED).build();
			Executable throwsError = () -> orderService.addItemToOrder(orderConfirmed, medicineActive, 3);
			assertThrows(CannotAddItemsException.class, throwsError);
			assertEquals(0, orderConfirmed.getItems().size());
		}
	}

	@Nested
	@DisplayName(".confirmOrder")
	class ConfirmOrder {
		@Test
		void willFail() {
			fail();
		}
	}

	@Nested
	@DisplayName(".dispatchOrder")
	class DispatchOrder {
		@Test
		void willFail() {
			fail();
		}
	}

	@Nested
	@DisplayName(".cancelOrder")
	class CancelOrder {
		@Test
		void willFail() {
			fail();
		}
	}
}
