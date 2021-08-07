package com.medicines.vendor.application.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicines.vendor.domain.users.vo.UserCpfMask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity @Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ApplicationUserDetails implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Email
	@Column(name = "username", unique = true)
	private String username;
	private String password;
	private ApplicationRole role;
	private String fullName;
	@CPF
	@Column(unique = true)
	@NotEmpty
	private String cpf;

	public ApplicationUserDetails(
		String username, String password, ApplicationRole role, String fullName, String cpf) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.fullName = fullName;
		this.cpf = cpf;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getGrantedAuthorities();
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getCpf() {
		return UserCpfMask.applyMask(cpf);
	}
}
