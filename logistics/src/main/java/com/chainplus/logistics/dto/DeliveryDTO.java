package com.chainplus.logistics.dto;

import lombok.Data;

import java.security.Timestamp;

@Data
public class DeliveryDTO {
    private Long deliveryId;
    private Long orderId;
    private String currentLocation;
    private String destination;
    private String status;
    private Timestamp estimatedTimeOfArrival;
}

