package com.chainplus.inventory.service.Impl;

import com.chainplus.inventory.dto.ProductDTO;
import com.chainplus.inventory.entity.Inventory;
import com.chainplus.inventory.entity.Product;
import com.chainplus.inventory.exception.ProductException;
import com.chainplus.inventory.exception.ProductNotFoundException;
import com.chainplus.inventory.mapper.ProductMapper;
import com.chainplus.inventory.repository.InventoryRepository;
import com.chainplus.inventory.repository.ProductRepository;
import com.chainplus.inventory.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.inventoryRepository = inventoryRepository;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        log.info("Attempting to create product: {}", productDTO);

        try {
            Product product = productMapper.toEntity(productDTO);
            Product savedProduct = productRepository.save(product);
            log.info("Product created with ID: {}", savedProduct.getId());

            Inventory inventory = new Inventory();
            inventory.setProduct(savedProduct);
            inventory.setQuantity(0);
            inventory.setWarehouseLocation(null);
            inventory.setLastUpdated(LocalDateTime.now());

            inventoryRepository.save(inventory);
            log.info("Inventory entry created for product ID: {} with NULL warehouse", savedProduct.getId());

            return productMapper.toDTO(savedProduct);

        } catch (Exception e) {
            log.error("Product creation failed: {}", e.getMessage(), e);
            throw new ProductException("Error occurred while creating product and inventory record", e);
        }
    }



    @Override
    public ProductDTO getProductById(Long productId) {
        log.info("Fetching product with ID: {}", productId);

        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

            return productMapper.toDTO(product);
        } catch (Exception e) {
            log.error("Error fetching product with ID {}: {}", productId, e.getMessage(), e);
            throw new ProductException("Error occurred while fetching product details", e);
        }
    }
}
