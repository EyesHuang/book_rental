package com.yohuang.bookrental.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BorrowRequest {
    private UUID userId;
    private UUID inventoryId;
}
