package com.medicines.vendor.domain.provider;

import com.medicines.vendor.domain.provider.vo.PermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Builder @Data
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(generator = "uuid")
	private UUID uuid;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private PermissionLevel permissionLevel;
}
