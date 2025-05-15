package com.chainplus.supplier.service.Impl;

import com.chainplus.supplier.dto.SupplierDTO;
import com.chainplus.supplier.entity.Supplier;
import com.chainplus.supplier.exception.SupplierNotFoundException;
import com.chainplus.supplier.mapper.SupplierMapper;
import com.chainplus.supplier.repository.SupplierRepository;
import com.chainplus.supplier.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierDTO getSupplierByName(String name) {
        return supplierRepository.findByName(name)
                .map(SupplierMapper::toDto)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDto) {
        Supplier supplier = SupplierMapper.toEntity(supplierDto);
        supplier = supplierRepository.save(supplier);
        return SupplierMapper.toDto(supplier);
    }
}

