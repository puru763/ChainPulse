package com.chainplus.orderprocessing.repository;


import com.chainplus.orderprocessing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long orderId);
}
