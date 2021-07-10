package com.medicines.vendor.domain.order.repository;

import com.medicines.vendor.domain.order.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
