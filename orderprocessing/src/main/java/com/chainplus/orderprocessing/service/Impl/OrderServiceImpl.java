//package com.chainplus.orderprocessing.service.Impl;
//
//
//import com.chainplus.orderprocessing.dto.OrderDTO;
//import com.chainplus.orderprocessing.entity.Order;
//import com.chainplus.orderprocessing.entity.OrderItem;
//import com.chainplus.orderprocessing.entity.OrderStatus;
//import com.chainplus.orderprocessing.exception.OrderNotFoundException;
//import com.chainplus.orderprocessing.mapper.OrderMapper;
//import com.chainplus.orderprocessing.repository.OrderRepository;
//import com.chainplus.orderprocessing.service.OrderService;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//@Service
//public class OrderServiceImpl  implements OrderService {
//
//
//    private static final Logger log =  LoggerFactory.getLogger(OrderServiceImpl.class);
//
//    private final OrderRepository orderRepository;
//    private final OrderMapper orderMapper;
//
//    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
//        this.orderRepository = orderRepository;
//        this.orderMapper = orderMapper;
//    }
//
//    @Transactional
//    public OrderDTO createOrder(OrderDTO dto) {
//        try {
//            Order order = orderMapper.toEntity(dto);
//            Order saved = orderRepository.save(order);
//            log.info("Order created with ID: {}", saved.getOrderId());
//            return orderMapper.toDto(saved);
//        } catch (Exception e) {
//            log.error("Error while creating order", e);
//            throw e; // or wrap in custom exception
//        }
//    }
//
//    @Transactional
//    public OrderDTO updateOrder(Long orderId, OrderDTO dto) {
//        try {
//            Order existing = orderRepository.findById(orderId)
//                    .orElseThrow(() -> new OrderNotFoundException(orderId));
//
//            existing.setCustomerId(dto.getCustomerId());
//            existing.setOrderDate(dto.getOrderDate());
//            existing.setStatus(dto.getStatus());
//
//            existing.getOrderItems().clear();
//            dto.getOrderItems().forEach(itemDto -> {
//                OrderItem item = orderMapper.toItemEntity(itemDto);
//                item.setOrder(existing);
//                existing.getOrderItems().add(item);
//            });
//
//            Order updated = orderRepository.save(existing);
//            log.info("Order updated with ID: {}", updated.getOrderId());
//            return orderMapper.toDto(updated);
//        } catch (OrderNotFoundException ex) {
//            log.warn("Update failed: {}", ex.getMessage());
//            throw ex;
//        } catch (Exception e) {
//            log.error("Error while updating order ID: {}", orderId, e);
//            throw e;
//        }
//    }
//
//    @Transactional
//    public void cancelOrder(Long orderId) {
//        try {
//            Order order = orderRepository.findById(orderId)
//                    .orElseThrow(() -> new OrderNotFoundException(orderId));
//            order.setStatus(OrderStatus.CANCELED);
//            orderRepository.save(order);
//            log.info("Order canceled with ID: {}", orderId);
//        } catch (OrderNotFoundException ex) {
//            log.warn("Cancel failed: {}", ex.getMessage());
//            throw ex;
//        } catch (Exception e) {
//            log.error("Error while canceling order ID: {}", orderId, e);
//            throw e;
//        }
//    }
//}



package com.chainplus.orderprocessing.service.impl;

import com.chainplus.orderprocessing.dto.OrderDTO;
import com.chainplus.orderprocessing.entity.Order;
import com.chainplus.orderprocessing.entity.OrderItem;
import com.chainplus.orderprocessing.entity.OrderStatus;
import com.chainplus.orderprocessing.exception.OrderNotFoundException;
import com.chainplus.orderprocessing.mapper.OrderMapper;
import com.chainplus.orderprocessing.repository.OrderRepository;
import com.chainplus.orderprocessing.service.OrderService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO dto) {
        try {
            Order order = orderMapper.toEntity(dto);
            Order saved = orderRepository.save(order);
            log.info("Order created with ID: {}", saved.getOrderId());
            return orderMapper.toDto(saved);
        } catch (Exception e) {
            log.error("Error while creating order", e);
            throw new RuntimeException("Failed to create order", e);
        }
    }

    @Transactional
    public OrderDTO updateOrder(Long orderId, OrderDTO dto) {
        try {
            Order existing = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(orderId));

            existing.setCustomerId(dto.getCustomerId());
            existing.setOrderDate(dto.getOrderDate());
            existing.setStatus(dto.getStatus());

            existing.getOrderItems().clear();
            dto.getOrderItems().forEach(itemDto -> {
                OrderItem item = orderMapper.toItemEntity(itemDto);
                item.setOrder(existing);
                existing.getOrderItems().add(item);
            });

            Order updated = orderRepository.save(existing);
            log.info("Order updated successfully with ID: {}", updated.getOrderId());
            return orderMapper.toDto(updated);
        } catch (OrderNotFoundException ex) {
            log.warn("Order update failed: {}", ex.getMessage());
            throw ex;
        } catch (Exception e) {
            log.error("Unexpected error while updating order ID: {}", orderId, e);
            throw new RuntimeException("Order update failed", e);
        }
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(orderId));

            order.setStatus(OrderStatus.CANCELED);
            orderRepository.save(order);
            log.info("Order successfully canceled for ID: {}", orderId);
        } catch (OrderNotFoundException ex) {
            log.warn("Order cancellation failed: {}", ex.getMessage());
            throw ex;
        } catch (Exception e) {
            log.error("Unexpected error while canceling order ID: {}", orderId, e);
            throw new RuntimeException("Order cancellation failed", e);
        }
    }
}