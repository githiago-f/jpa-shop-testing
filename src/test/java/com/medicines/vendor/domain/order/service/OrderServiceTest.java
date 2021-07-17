package com.medicines.vendor.domain.order.service;

import com.medicines.vendor.domain.order.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("# OrderService")
class OrderServiceTest {
	@Mock private OrderRepository orderRepository;
	@InjectMocks private OrderService orderService;

	@Nested
	@DisplayName(".openOrder")
	class OpenOrder {
		@Test
		void willFail() {
			fail();
		}
	}

	@Nested
	@DisplayName(".addItemToOrder")
	class AddItemToOrder {
		@Test
		void willFail() {
			fail();
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
