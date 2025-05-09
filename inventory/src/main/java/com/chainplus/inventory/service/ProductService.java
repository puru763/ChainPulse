package com.chainplus.inventory.service;

import com.chainplus.inventory.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO getProductById(Long productId);
}
