package com.chainplus.inventory.repository;

import com.chainplus.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository  extends JpaRepository<Product , Long> {
}
