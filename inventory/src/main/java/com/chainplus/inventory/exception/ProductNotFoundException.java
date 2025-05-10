package com.chainplus.inventory.exception;

public class ProductNotFoundException    extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}