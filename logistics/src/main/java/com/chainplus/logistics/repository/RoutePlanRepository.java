package com.chainplus.logistics.repository;

import com.chainplus.logistics.entity.RoutePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutePlanRepository extends JpaRepository<RoutePlan, Long> {
    List<RoutePlan> findByDeliveryId(Long deliveryId);
}

