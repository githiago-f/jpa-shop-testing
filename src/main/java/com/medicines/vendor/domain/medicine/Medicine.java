package com.medicines.vendor.domain.medicine;

import com.fasterxml.jackson.annotation.*;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Nullable
	@OneToOne(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	@JsonManagedReference
	private Datasheet datasheet;

	@PrePersist()
	void prePersist() {
		createdAt = LocalDateTime.now();
	}

	public void enable() { state = MedicineState.ACTIVE; }
	public boolean isWaitingDatasheet() { return state.equals(MedicineState.DATASHEET_REQUIRED); }
	public boolean isActive() { return state.equals(MedicineState.ACTIVE);  }
	public void setDatasheet(Datasheet datasheet) {
		this.datasheet = datasheet;
	}
}
