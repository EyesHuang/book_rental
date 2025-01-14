package com.yohuang.bookrental.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryUserResponse {
    private UUID id;
    private LocalDateTime loanDate;
    private UserResponse user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
