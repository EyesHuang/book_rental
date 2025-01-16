package com.yohuang.bookrental.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
public class Inventory {
    @Id
    @UuidGenerator
    private String id;

    private LocalDateTime loanDate;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private AppUser user;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="book_id")
    private Book book;
}
