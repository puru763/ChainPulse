package com.chainplus.logistics.service;

import com.chainplus.logistics.dto.DeliveryDTO;

public interface DeliveryService {
    DeliveryDTO getDeliveryByOrderId(Long orderId);
/*
    void updateDeliveryStatus(Long deliveryId, DeliveryStatus status);
*/
}
