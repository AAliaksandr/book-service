package com.aksionav.bookservice.model.dto;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String authorName;
}
