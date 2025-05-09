package com.chainplus.inventory.dto;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ForecastDTO {
    private Long id;
    private Long productId;
    private Integer predictedDemand;
    private LocalDate forecastDate;
}

