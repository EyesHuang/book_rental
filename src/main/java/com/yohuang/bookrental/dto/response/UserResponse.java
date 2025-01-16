package com.yohuang.bookrental.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String role;
    private List<InventoryBookResponse> inventories;
}
