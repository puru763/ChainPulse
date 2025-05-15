package com.chainplus.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceMetricsDto {
    private double onTimeDeliveryPercentage;
    private int qualityRating;
}

