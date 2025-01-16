package com.yohuang.bookrental.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BorrowRequest {
    private String userId;
    private String inventoryId;
}
