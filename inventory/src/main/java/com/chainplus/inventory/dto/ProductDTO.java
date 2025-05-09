package com.chainplus.inventory.dto;
import lombok.*;

import java.math.BigDecimal;


@Data

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal unitPrice;
}
