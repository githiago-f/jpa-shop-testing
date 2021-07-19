package com.medicines.vendor.domain.order;

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

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private final List<OrderItem> items = new ArrayList<>();

	@Transient
	private BigDecimal total;

	public void addItem(OrderItem item) {
		this.items.add(item);
	}

	@JsonIgnore
	public boolean canAddItems() {
		return state.equals(OrderState.TO_CONFIRM);
	}

	public BigDecimal getTotal() {
		return items.stream()
			.parallel()
			.map(OrderItem::getTotalPrice)
			.reduce(new BigDecimal(0), BigDecimal::add);
	}
}
