package com.chainplus.inventory.service.Impl;

import com.chainplus.inventory.dto.InventoryDTO;
import com.chainplus.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class InventoryServiceImpl implements InventoryService {
    @Override
    public List<InventoryDTO> getAllInventory() {
        return List.of();
    }

    @Override
    public InventoryDTO updateInventory(InventoryDTO inventoryUpdate) {
        return null;
    }

    @Override
    public String getInventoryForecast(Long productId) {
        return "";
    }
}
