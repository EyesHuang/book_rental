package com.yohuang.bookrental.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="author")
    private String author;

    @Column(name="title")
    private String title;

    @Column(name="image")
    private String image;

    @OneToMany(mappedBy="book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Inventory> inventories;
}
