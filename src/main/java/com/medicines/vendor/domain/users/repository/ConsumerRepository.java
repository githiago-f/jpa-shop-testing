package com.medicines.vendor.domain.users.repository;

import com.medicines.vendor.domain.users.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
