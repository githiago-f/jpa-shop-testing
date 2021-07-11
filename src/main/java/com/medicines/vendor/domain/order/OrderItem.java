package com.medicines.vendor.domain.order;

import com.medicines.vendor.domain.medicine.Medicine;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Integer quantity;
	private BigDecimal unitPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	@ManyToOne(fetch = FetchType.LAZY)
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
