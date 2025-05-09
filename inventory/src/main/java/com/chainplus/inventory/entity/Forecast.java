package com.chainplus.inventory.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forecast")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @Column(nullable = false)
    private Integer predictedDemand;

    @Column(nullable = false)
    private LocalDate forecastDate;

}