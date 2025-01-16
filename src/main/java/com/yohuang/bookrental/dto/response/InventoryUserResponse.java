package com.yohuang.bookrental.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InventoryUserResponse {
    private String id;
    private LocalDateTime loanDate;
    private UserResponse user;
}