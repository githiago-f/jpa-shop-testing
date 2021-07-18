package com.medicines.vendor.domain.order;

import com.medicines.vendor.domain.order.vo.OrderState;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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

	@Enumerated(EnumType.STRING)
	private OrderState state;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

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
