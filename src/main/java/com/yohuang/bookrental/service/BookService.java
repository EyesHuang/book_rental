package com.yohuang.bookrental.service;

import com.yohuang.bookrental.dao.*;
import com.yohuang.bookrental.dto.request.BorrowRequest;
import com.yohuang.bookrental.dto.response.*;
import com.yohuang.bookrental.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;

    public List<BookResponse> getAllBooks(int offset, int limit) {
        return bookRepository.findAll()
                .stream()
                .skip(offset)
                .limit(limit)
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return toBookResponse(book);
    }

    @Transactional
    public void borrowBook(BorrowRequest request) {
        Inventory inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getUser() != null) {
            throw new RuntimeException("Book is already borrowed");
        }

        AppUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        inventory.setUser(user);
        inventory.setLoanDate(LocalDateTime.now());
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void returnBook(BorrowRequest request) {
        Inventory inventory = inventoryRepository.findByIdAndUserId(
                        request.getInventoryId(), request.getUserId())
                .orElseThrow(() -> new RuntimeException("Book not found or not borrowed by this user"));

        inventory.setUser(null);
        inventory.setLoanDate(null);
        inventoryRepository.save(inventory);
    }

    private BookResponse toBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setImage(book.getImage());
        response.setInventories(book.getInventories().stream()
                .map(this::toInventoryUserResponse)
                .collect(Collectors.toList()));
        return response;
    }

    private InventoryUserResponse toInventoryUserResponse(Inventory inventory) {
        InventoryUserResponse response = new InventoryUserResponse();
        response.setId(inventory.getId());
        response.setLoanDate(inventory.getLoanDate());
        if (inventory.getUser() != null) {
            response.setUser(toUserResponse(inventory.getUser()));
        }
        return response;
    }

    private UserResponse toUserResponse(AppUser user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }
}
