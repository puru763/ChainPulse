//package com.chainplus.orderprocessing.controller;
//
//import com.chainplus.orderprocessing.dto.OrderDTO;
//import com.chainplus.orderprocessing.service.OrderService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/orders")
//@RequiredArgsConstructor
//public class OrderController {
//
//
//        @Autowired
//        private  final OrderService orderService;
//
//
//    @PostMapping
//    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
//        OrderDTO createdOrder = orderService.createOrder(orderDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder); // 201 Created
//    }
//
//    @PutMapping("/{orderId}")
//    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderDTO orderDTO) {
//        OrderDTO updatedOrder = orderService.updateOrder(orderId, orderDTO);
//        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);    }
//
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
//        orderService.cancelOrder(orderId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);     }
//}



package com.chainplus.orderprocessing.controller;

import com.chainplus.orderprocessing.dto.OrderDTO;
import com.chainplus.orderprocessing.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Autowired
    public OrderController(OrderService orderService, KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }



    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        kafkaTemplate.send("ORDER_CREATED", orderDTO); // 🔹 Publish event instead of direct DB call
        return ResponseEntity.status(HttpStatus.CREATED).body("Order placed, awaiting inventory validation.");
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(orderId, orderDTO);
        kafkaTemplate.send("ORDER_UPDATED", orderDTO); // 🔹 Notify inventory of changes
        return ResponseEntity.ok("Order updated, inventory adjustments pending.");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        kafkaTemplate.send("ORDER_CANCELED", orderId.toString()); // 🔹 Notify inventory for stock rollback
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order canceled.");
    }
}