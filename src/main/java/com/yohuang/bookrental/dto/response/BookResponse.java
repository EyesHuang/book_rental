package com.yohuang.bookrental.dto.response;

import java.util.List;
import java.util.UUID;

public class BookResponse {
    private UUID id;
    private String title;
    private String author;
    private String image;
    private List<InventoryUserResponse> inventories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<InventoryUserResponse> getInventories() {
        return inventories;
    }

    public void setInventories(List<InventoryUserResponse> inventories) {
        this.inventories = inventories;
    }
}
