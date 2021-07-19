package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.domain.order.Order;
import com.medicines.vendor.domain.order.OrderItem;
import com.medicines.vendor.domain.order.repository.OrderRepository;
import com.medicines.vendor.domain.order.service.errors.CannotAddItemsException;
import com.medicines.vendor.domain.order.service.errors.MedicineInactiveException;
import com.medicines.vendor.domain.order.vo.OrderState;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("# OrderService")
class OrderServiceTest {
	@Mock private OrderRepository orderRepository;
	@InjectMocks private OrderService orderService;

	protected Medicine medicineActive, medicineInactive;

	@BeforeEach
	void setUp() {
		medicineActive = Medicine.builder().state(MedicineState.ACTIVE).build();
		medicineInactive = Medicine.builder().state(MedicineState.DATASHEET_REQUIRED).build();
	}

	@Nested
	@DisplayName(".openOrder")
	class OpenOrder {
		@Test
		@DisplayName("at last one item is present")
		void atLastOneOrderItemIsPresent() { fail(); }
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
