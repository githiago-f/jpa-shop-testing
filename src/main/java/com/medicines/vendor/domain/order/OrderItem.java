package com.medicines.vendor.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Builder @NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Integer quantity;
	private BigDecimal unitPrice;

	@ManyToOne
	private Order order;
	@ManyToOne
	private Medicine medicine;

	public OrderItem(Integer quantity, Order order, Medicine medicine) {
		this.quantity = quantity;
		this.order = order;
		this.medicine = medicine;
		this.unitPrice = medicine.getPrice();
	}

	public BigDecimal getTotalPrice() {
		return unitPrice.multiply(new BigDecimal(quantity));
	}
}
