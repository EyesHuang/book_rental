package com.yohuang.bookrental.dao;

import com.yohuang.bookrental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    List<Inventory> findByBookId(UUID bookId);
    Optional<Inventory> findByIdAndUserId(UUID id, UUID userId);
}
