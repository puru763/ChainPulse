package com.chainplus.orderprocessing.mapper;

import com.chainplus.orderprocessing.dto.OrderItemDTO;
import com.chainplus.orderprocessing.entity.OrderItem;

public class OrderItemMapper {

    public OrderItemDTO toDto(OrderItem item) {
        if (item == null) return null;

        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    public OrderItem toEntity(OrderItemDTO dto) {
        if (dto == null) return null;

        OrderItem item = new OrderItem();
        item.setId(dto.getId());
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return item;
    }
}

