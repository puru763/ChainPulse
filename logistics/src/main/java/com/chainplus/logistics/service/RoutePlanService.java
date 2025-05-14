package com.chainplus.logistics.service;

import com.chainplus.logistics.dto.RoutePlanDTO;

import java.util.List;

public interface RoutePlanService {
    RoutePlanDTO optimizeRoute(RoutePlanDTO routePlanDTO);

    List<RoutePlanDTO> getRoutesByDeliveryId(Long deliveryId);
}
