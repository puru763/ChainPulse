package com.chainplus.inventory.service.Impl;

import com.chainplus.inventory.dto.InventoryDTO;
import com.chainplus.inventory.entity.Inventory;
import com.chainplus.inventory.exception.InventoryException;
import com.chainplus.inventory.exception.ProductNotFoundException;
import com.chainplus.inventory.exception.ResourceNotFoundException;
import com.chainplus.inventory.mapper.InventoryMapper;
import com.chainplus.inventory.repository.InventoryRepository;
import com.chainplus.inventory.repository.ProductRepository;
import com.chainplus.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }



    @Override
    public String getInventoryForecast(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
        int demandForecast = calculateDemandForecast(inventory);
        return "Predicted Demand: " + demandForecast;
    }
    private int calculateDemandForecast(Inventory inventory) {
        return (int) (inventory.getQuantity() * 1.2);
    }




    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toDTO)
                .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public InventoryDTO updateInventory(Long productId, InventoryDTO inventoryUpdateDTO) {
        log.info("Updating inventory for Product ID: {}", productId);

        try {
            Inventory inventory = inventoryRepository.findByProductId(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Inventory record not found for Product ID: " + productId));

            if (inventoryUpdateDTO.getWarehouseLocation() == null || inventoryUpdateDTO.getWarehouseLocation().isEmpty()) {
                throw new InventoryException("Warehouse location must be specified", null);
            }

            if (inventoryUpdateDTO.getQuantity() == null || inventoryUpdateDTO.getQuantity() <= 0) {
                throw new InventoryException("Invalid quantity value", null);
            }

            int updatedQuantity = inventory.getQuantity() + inventoryUpdateDTO.getQuantity();
            inventory.setQuantity(updatedQuantity);
            inventory.setWarehouseLocation(inventoryUpdateDTO.getWarehouseLocation());
            inventory.setLastUpdated(LocalDateTime.now());

            inventoryRepository.save(inventory);

            log.info("Inventory updated: Product ID {} → Added {} units (New Total: {}) at {}",
                    productId, inventoryUpdateDTO.getQuantity(), updatedQuantity, inventoryUpdateDTO.getWarehouseLocation());

            return inventoryMapper.toDTO(inventory);
        } catch (Exception e) {
            log.error("Error updating inventory for Product ID {}: {}", productId, e.getMessage(), e);
            throw new InventoryException("Error updating inventory stock", e);
        }
    }

}
