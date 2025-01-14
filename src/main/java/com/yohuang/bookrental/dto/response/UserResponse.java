package com.yohuang.bookrental.dto.response;

import java.util.List;
import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String username;
    private String role;
    private List<InventoryBookResponse> inventories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<InventoryBookResponse> getInventories() {
        return inventories;
    }

    public void setInventories(List<InventoryBookResponse> inventories) {
        this.inventories = inventories;
    }
}
