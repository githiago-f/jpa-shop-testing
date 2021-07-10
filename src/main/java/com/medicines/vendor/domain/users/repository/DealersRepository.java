package com.medicines.vendor.domain.users.repository;

import com.medicines.vendor.domain.users.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealersRepository extends JpaRepository<Dealer, Long> {
}
