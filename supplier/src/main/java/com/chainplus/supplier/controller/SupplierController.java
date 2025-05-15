package com.chainplus.supplier.controller;

import com.chainplus.supplier.dto.SupplierDTO;
import com.chainplus.supplier.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<SupplierDTO> getSupplierByName(@PathVariable String name) {
        return ResponseEntity.ok(supplierService.getSupplierByName(name));
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.createSupplier(supplierDto));
    }
}
