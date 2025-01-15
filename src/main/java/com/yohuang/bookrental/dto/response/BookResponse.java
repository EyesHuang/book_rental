package com.yohuang.bookrental.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BookResponse {
    private UUID id;
    private String title;
    private String author;
    private String image;
    private List<InventoryUserResponse> inventories;
}
