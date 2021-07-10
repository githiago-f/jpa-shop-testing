package com.medicines.vendor.domain.users;

import com.medicines.vendor.domain.users.vo.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "cnpj", unique = true, updatable = false)
    private String CNPJ;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private final UserType type = UserType.PROVIDER;
}
