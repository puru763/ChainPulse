package com.chainplus.logistics.controller;

import com.chainplus.logistics.dto.RoutePlanDTO;
import com.chainplus.logistics.service.RoutePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logistics/route")
public class RouteOptimizationController {

    private final RoutePlanService routePlanService;

    @Autowired
    public RouteOptimizationController(RoutePlanService routePlanService) {
        this.routePlanService = routePlanService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<RoutePlanDTO> optimizeRoute(@RequestBody RoutePlanDTO routePlanDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(routePlanService.optimizeRoute(routePlanDTO));
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<List<RoutePlanDTO>> getRoutes(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(routePlanService.getRoutesByDeliveryId(deliveryId)); // No manual checks needed
    }



}

