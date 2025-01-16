package com.yohuang.bookrental.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
public class Book {
    @Id
    @UuidGenerator
    private String id;

    private String author;

    private String title;

    private String image;
}
