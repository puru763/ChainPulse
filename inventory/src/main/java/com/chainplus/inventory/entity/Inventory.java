package com.chainplus.inventory.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;



 @Data
 @NoArgsConstructor
 @Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "product_id", nullable = false)
     private Product product;

     @Column(nullable = false)
     private Integer quantity;

     @Column(nullable = false)
     private String warehouseLocation;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

}