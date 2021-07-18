package com.medicines.vendor.domain.order.repository;

import com.medicines.vendor.domain.order.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
