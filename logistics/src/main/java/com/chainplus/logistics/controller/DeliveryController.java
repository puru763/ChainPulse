package com.chainplus.logistics.controller;

import com.chainplus.logistics.dto.DeliveryDTO;
import com.chainplus.logistics.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logistics/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable Long orderId) {
        DeliveryDTO deliveryDTO = deliveryService.getDeliveryByOrderId(orderId);
        if (deliveryDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(deliveryDTO);
    }


//    @PutMapping("/{deliveryId}/status")
//    public ResponseEntity<Void> updateStatus(@PathVariable Long deliveryId, @RequestParam DeliveryStatus status) {
//        boolean updated = deliveryService.updateDeliveryStatus(deliveryId, status);
//        if (!updated) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

}
