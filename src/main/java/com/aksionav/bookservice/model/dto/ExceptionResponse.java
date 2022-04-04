package com.aksionav.bookservice.model.dto;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String errorMessage;
    private String callerUrl;
}
