package com.yohuang.bookrental.repository;

import com.yohuang.bookrental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findByBookId(String bookId);

    List<Inventory> findByUserId(String userId);

    Optional<Inventory> findByIdAndUserId(String id, String userId);
}
