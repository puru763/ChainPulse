package com.chainplus.orderprocessing.controller;

import com.chainplus.orderprocessing.dto.OrderDTO;
import com.chainplus.orderprocessing.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {


        @Autowired
        private  final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder); // 201 Created
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(orderId, orderDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);     }
}
