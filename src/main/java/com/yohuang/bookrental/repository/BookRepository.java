package com.yohuang.bookrental.repository;

import com.yohuang.bookrental.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
