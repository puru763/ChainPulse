package com.chainplus.inventory.service;

import com.chainplus.inventory.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();

    InventoryDTO updateInventory(InventoryDTO inventoryUpdate);

    String getInventoryForecast(Long productId);
}
