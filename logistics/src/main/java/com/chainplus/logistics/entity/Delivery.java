package com.chainplus.logistics.entity;

import com.chainplus.logistics.mapper.DeliveryStatus;
import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.List;


@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Column(nullable = false)
    private Long orderId;

    private String currentLocation;
    private String destination;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Timestamp estimatedTimeOfArrival;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<RoutePlan> routePlans;

    private Timestamp lastUpdated;
}

