package com.yohuang.bookrental.dto.request;

import java.util.UUID;

public class BorrowRequest {
    private UUID userId;
    private UUID inventoryId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(UUID inventoryId) {
        this.inventoryId = inventoryId;
    }
}
