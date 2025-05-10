package com.chainplus.orderprocessing.dto;

import com.chainplus.orderprocessing.entity.OrderStatus;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
public class OrderDTO {

    private Long orderId;

    @NotNull(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Order date is required")
    private Timestamp orderDate;

    @NotNull(message = "Status is required")
    private OrderStatus status;

    @NotNull(message = "Order must have at least one item")
    @Size(min = 1, message = "Order must contain at least one item")
    private List<OrderItemDTO> orderItems;
}

