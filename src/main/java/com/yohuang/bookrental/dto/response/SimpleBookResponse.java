package com.yohuang.bookrental.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class SimpleBookResponse {
    private String id;
    private String title;
    private String author;
    private String image;
}
