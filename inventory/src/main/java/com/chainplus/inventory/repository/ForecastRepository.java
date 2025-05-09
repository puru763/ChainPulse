package com.chainplus.inventory.repository;


import com.chainplus.inventory.entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForecastRepository   extends JpaRepository <Forecast, Long> {
    // Add custom query methods if needed
}
