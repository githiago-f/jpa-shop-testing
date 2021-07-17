package com.medicines.vendor.domain.order;

import com.medicines.vendor.domain.medicine.Medicine;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
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

	public BigDecimal getTotalPrice() {
		return unitPrice.multiply(new BigDecimal(quantity));
	}
}
