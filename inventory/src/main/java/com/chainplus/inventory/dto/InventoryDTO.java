package com.chainplus.inventory.dto;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class InventoryDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String warehouseLocation;
    private LocalDateTime lastUpdated;
}

