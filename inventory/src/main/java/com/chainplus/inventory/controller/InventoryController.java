package com.chainplus.inventory.controller;

import com.chainplus.inventory.dto.InventoryDTO;
import com.chainplus.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        List<InventoryDTO> inventoryDTOList = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryDTOList);
    }

    @PostMapping("/update")
    public ResponseEntity<InventoryDTO> updateInventory(@RequestBody InventoryDTO inventoryUpdateDTO) {
        InventoryDTO updatedInventoryDTO = inventoryService.updateInventory(inventoryUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedInventoryDTO);
    }

    @GetMapping("/forecast/{productId}")
    public ResponseEntity<String> getForecast(@PathVariable Long productId) {
        String forecast = inventoryService.getInventoryForecast(productId);
        return ResponseEntity.ok(forecast);
    }
}
