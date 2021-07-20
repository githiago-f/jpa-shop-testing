package com.medicines.vendor.domain.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medicines.vendor.domain.order.service.errors.*;
import com.medicines.vendor.domain.order.vo.OrderState;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private OrderState state;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private final List<OrderItem> items = new ArrayList<>();
	private BigDecimal total;

	public void addItem(OrderItem item) {
		if(!canAddItems()) {
			throw new CannotAddItemsException();
		}
		this.items.add(item);
	}

	@JsonIgnore
	public boolean canAddItems() {
		return state.equals(OrderState.TO_CONFIRM);
	}

	@PrePersist
	@PreUpdate
	public void calculateTotal() {
		total = items.stream()
			.parallel()
			.map(OrderItem::getTotalPrice)
			.reduce(new BigDecimal("0.00"), BigDecimal::add);
	}

	public void confirm() {
		if(!canConfirm()) {
			throw new CannotConfirmOrderException();
		}
		state = OrderState.CONFIRMED;
	}

	public boolean canConfirm() {
		return state.equals(OrderState.TO_CONFIRM);
	}
}
