package com.chainplus.orderprocessing.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;
}
