package com.medicines.vendor.domain.order;

import com.medicines.vendor.domain.order.vo.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	@Enumerated(EnumType.STRING)
	private OrderState state;
	private String client;
	@Transient
	private BigDecimal total;

	public void addItem(OrderItem item) {
		this.items.add(item);
	}

	public BigDecimal getTotal() {
		return items.stream()
			.parallel()
			.map(OrderItem::getTotalPrice)
			.reduce(new BigDecimal(0), BigDecimal::add);
	}
}
