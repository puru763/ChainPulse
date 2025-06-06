package com.chainplus.inventory.service;

import com.chainplus.inventory.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();


    String getInventoryForecast(Long productId);

    InventoryDTO updateInventory(Long productId, InventoryDTO inventoryUpdateDTO);
}
