package com.yohuang.bookrental.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryBookResponse {
    private UUID id;
    private LocalDateTime loanDate;
    private SimpleBookResponse book;

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

    public SimpleBookResponse getBook() {
        return book;
    }

    public void setBook(SimpleBookResponse book) {
        this.book = book;
    }
}
