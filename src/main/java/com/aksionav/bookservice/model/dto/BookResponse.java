package com.aksionav.bookservice.model.dto;

import lombok.Data;

@Data
public class BookResponse {
    private Integer code;
    private String title;
    private String authorName;
}
