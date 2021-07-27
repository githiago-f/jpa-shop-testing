package com.medicines.vendor.domain.provider;

import com.medicines.vendor.application.security.AccessDetails;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class User {
	@Id
	@GeneratedValue(generator = "uuid")
	private UUID uuid;
	private String name;
	@Embedded
	@JsonIgnore
	private AccessDetails accessDetails;
}
