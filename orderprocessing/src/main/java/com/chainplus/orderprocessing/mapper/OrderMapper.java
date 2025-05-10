package com.chainplus.orderprocessing.mapper;

import com.chainplus.orderprocessing.dto.OrderDTO;
import com.chainplus.orderprocessing.dto.OrderItemDTO;
import com.chainplus.orderprocessing.entity.Order;
import com.chainplus.orderprocessing.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public OrderDTO toDto(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerId(order.getCustomerId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());

        if (order.getOrderItems() != null) {
            dto.setOrderItems(order.getOrderItems().stream()
                    .map(this::toItemDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public OrderItemDTO toItemDto(OrderItem item) {
        if (item == null) return null;

        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) return null;

        if (dto.getOrderItems() == null || dto.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setCustomerId(dto.getCustomerId());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());

        List<OrderItem> items = dto.getOrderItems().stream()
                .map(this::toItemEntity)
                .collect(Collectors.toList());

        // Set back-reference from item → order
        for (OrderItem item : items) {
            item.setOrder(order);
        }

        order.setOrderItems(items);
        return order;
    }

    public OrderItem toItemEntity(OrderItemDTO dto) {
        if (dto == null) return null;

        OrderItem item = new OrderItem();
        item.setId(dto.getId());
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return item;
    }
}
