package com.chainplus.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class InventoryDTO {
    private Long id;
    private Long productId;
    private Integer quantity;


    @NotNull(message = "Warehouse location cannot be null when updating inventory")
    private String warehouseLocation;

    private LocalDateTime lastUpdated;
}
