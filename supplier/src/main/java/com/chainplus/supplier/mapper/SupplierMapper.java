package com.chainplus.supplier.mapper;

import com.chainplus.supplier.dto.SupplierDTO;
import com.chainplus.supplier.entity.ContactInfo;
import com.chainplus.supplier.entity.PerformanceMetrics;
import com.chainplus.supplier.entity.Supplier;

import java.util.stream.Collectors;

public class SupplierMapper {
    public static SupplierDTO toDto(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId().toString(),
                supplier.getName(),
                new ContactInfoDto(supplier.getContactInfo()),
                new PerformanceMetricsDto(supplier.getPerformanceMetrics()),
                supplier.getPricingTrends().stream().map(PriceTrendDto::new).collect(Collectors.toList()),
                supplier.getRiskScore()
        );
    }

    public static Supplier toEntity(SupplierDTO dto) {
        return new Supplier(
                dto.getId() == null ? new ObjectId() : new ObjectId(dto.getId()),
                dto.getName(),
                new ContactInfo(dto.getContactInfo()),
                new PerformanceMetrics(dto.getPerformanceMetrics()),
                dto.getPricingTrends().stream().map(PriceTrend::new).collect(Collectors.toList()),
                dto.getRiskScore()
        );
    }
}

