
package com.chainplus.orderprocessing.exception;

    public class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(Long orderId) {
            super("Order with ID " + orderId + " not found");
        }
    }