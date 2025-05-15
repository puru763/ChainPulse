package com.chainplus.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private String id;
    private String name;
    private ContactInfoDto contactInfo;
    private PerformanceMetricsDto performanceMetrics;
    private List<PriceTrendDto> pricingTrends;
    private int riskScore;
}

