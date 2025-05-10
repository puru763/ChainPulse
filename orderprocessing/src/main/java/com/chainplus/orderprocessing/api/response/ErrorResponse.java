package com.chainplus.orderprocessing.api.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
