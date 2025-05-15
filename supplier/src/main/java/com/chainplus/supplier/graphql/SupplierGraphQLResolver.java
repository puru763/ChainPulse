package com.chainplus.supplier.graphql;

import com.chainplus.supplier.dto.SupplierDTO;
import com.chainplus.supplier.exception.SupplierNotFoundException;
import com.chainplus.supplier.mapper.SupplierMapper;
import com.chainplus.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierGraphQLResolver implements GraphQLQueryResolver {

    private final SupplierRepository supplierRepository;

    public SupplierGraphQLResolver(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierDTO getSupplierByName(String name) {
        return supplierRepository.findByName(name)
                .map(SupplierMapper::toDto)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
    }

    public List<SupplierDTO> listSuppliers(@Argument Integer riskScore) {
        return supplierRepository.findAll().stream()
                .filter(s -> s.getRiskScore() > riskScore)
                .map(SupplierMapper::toDto)
                .collect(Collectors.toList());
    }
}
