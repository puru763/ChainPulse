package com.chainplus.orderprocessing.service;

import com.chainplus.orderprocessing.dto.OrderDTO;
import jakarta.validation.Valid;

public interface OrderService {
    OrderDTO createOrder(@Valid OrderDTO orderDTO);

    OrderDTO updateOrder(Long orderId, @Valid OrderDTO orderDTO);

    void cancelOrder(Long orderId);
}
