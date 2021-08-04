package com.medicines.vendor.domain.medicine;

import com.fasterxml.jackson.annotation.*;
import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.medicines.vendor.domain.medicine.vo.MedicineState.*;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine {
	@Id
	private String code;

	@Column(name = "commercial_name")
	private String name;
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private MedicineState state;

	@Nullable
	@OneToOne(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	@JsonManagedReference
	private Datasheet datasheet;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Laboratory laboratory;
	@Transient
	private String laboratoryCnpj;

	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@PrePersist()
	void prePersist() {
		createdAt = LocalDateTime.now();
	}

	public void enable() { state = ACTIVE; }
	@JsonIgnore
	public boolean isWaitingDatasheet() {
		return state.equals(DATASHEET_REQUIRED);
	}
	@JsonIgnore
	public boolean isActive() {
		return state.equals(ACTIVE);
	}
	@JsonProperty("laboratory_cnpj")
	public String getLaboratoryCnpj() {
		return laboratory.getCnpj();
	}
	public void setDatasheet(Datasheet datasheet) {
		this.datasheet = datasheet;
	}
}
