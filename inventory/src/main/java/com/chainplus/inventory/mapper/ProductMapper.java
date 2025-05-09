package com.chainplus.inventory.mapper;

import com.chainplus.inventory.dto.ProductDTO;
import com.chainplus.inventory.entity.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setUnitPrice(product.getUnitPrice());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());
        entity.setUnitPrice(dto.getUnitPrice());
        return entity;
    }
}
