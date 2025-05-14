package com.chainplus.logistics.entity;

public enum DeliveryStatus {
    IN_TRANSIT,   // The package is currently being transported
    DELAYED,      // The package is delayed due to unforeseen issues
    DELIVERED,    // The package has reached its destination successfully
    FAILED        // Delivery attempt was unsuccessful
}
