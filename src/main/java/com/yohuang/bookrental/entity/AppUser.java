package com.yohuang.bookrental.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
public class AppUser {
    @Id
    @UuidGenerator
    private String id;

    private String username;

    private String password;

    private String role;
}
