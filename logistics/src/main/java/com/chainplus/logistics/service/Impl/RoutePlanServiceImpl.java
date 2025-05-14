package com.chainplus.logistics.service.Impl;

import com.chainplus.logistics.dto.RoutePlanDTO;
import com.chainplus.logistics.entity.RoutePlan;
import com.chainplus.logistics.exception.RouteNotFoundException;
import com.chainplus.logistics.mapper.RoutePlanMapper;
import com.chainplus.logistics.repository.RoutePlanRepository;
import com.chainplus.logistics.service.RoutePlanService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoutePlanServiceImpl implements RoutePlanService {

    private final RoutePlanRepository routePlanRepository;
    private final RoutePlanMapper routePlanMapper;

    @Autowired
    public RoutePlanServiceImpl(RoutePlanRepository routePlanRepository, RoutePlanMapper routePlanMapper) {
        this.routePlanRepository = routePlanRepository;
        this.routePlanMapper = routePlanMapper;
    }

    @Override
    public List<RoutePlanDTO> getRoutesByDeliveryId(Long deliveryId) {
        try {
            List<RoutePlan> routes = routePlanRepository.findByDeliveryId(deliveryId);

            if (routes.isEmpty()) {
                throw new RouteNotFoundException("No routes found for Delivery ID: " + deliveryId);
            }

            return routes.stream().map(routePlanMapper::toDTO).toList();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving routes for Delivery ID: " + deliveryId, e);
        }
    }

    @Override
    public RoutePlanDTO optimizeRoute(RoutePlanDTO routePlanDTO) {
        try {
            RoutePlan routePlan = routePlanMapper.toEntity(routePlanDTO);
            RoutePlan savedRoutePlan = routePlanRepository.save(routePlan);
            return routePlanMapper.toDTO(savedRoutePlan);
        } catch (Exception e) {
            throw new ServiceException("Error optimizing route", e);
        }
    }
}
