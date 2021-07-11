package com.medicines.vendor.domain.medicine;

import com.medicines.vendor.domain.order.vo.MedicineState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine {
	@Id
	private String code;
	@Column(name = "commercial_name")
	private String name;
	@Column(name = "factory_price")
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private MedicineState state;
}
