package com.chainplus.logistics.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "route_plans")
public class RoutePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @Lob
    private String routeDetails;

    private Timestamp lastUpdatedTime;
}


