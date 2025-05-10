package com.chainplus.inventory.mapper;

import com.chainplus.inventory.dto.InventoryDTO;
import com.chainplus.inventory.entity.Inventory;
import com.chainplus.inventory.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InventoryMapper {

    public InventoryDTO toDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProduct().getId());
        dto.setQuantity(inventory.getQuantity());
        dto.setWarehouseLocation(inventory.getWarehouseLocation());
        dto.setLastUpdated(inventory.getLastUpdated());
        return dto;
    }

    public Inventory toEntity(InventoryDTO dto, Product product) {
        Inventory inventory = new Inventory();
        inventory.setId(dto.getId());
        inventory.setProduct(product);
        inventory.setQuantity(dto.getQuantity());
        inventory.setWarehouseLocation(dto.getWarehouseLocation());
        inventory.setLastUpdated(dto.getLastUpdated());
        return inventory;
    }

    public void mergeEntity(Inventory inventory, InventoryDTO dto, Product product) {
        inventory.setProduct(product);
        inventory.setQuantity(inventory.getQuantity() + dto.getQuantity());
        inventory.setWarehouseLocation(dto.getWarehouseLocation());
        inventory.setLastUpdated(LocalDateTime.now());
    }
}
