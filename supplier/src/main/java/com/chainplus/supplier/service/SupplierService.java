package com.chainplus.supplier.service;

import com.chainplus.supplier.dto.SupplierDTO;

public interface SupplierService {
    public SupplierDTO getSupplierByName(String name)

    public SupplierDTO createSupplier(SupplierDTO supplierDTO)
}
