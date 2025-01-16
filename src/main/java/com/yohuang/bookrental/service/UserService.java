package com.yohuang.bookrental.service;

import com.yohuang.bookrental.repository.InventoryRepository;
import com.yohuang.bookrental.repository.UserRepository;
import com.yohuang.bookrental.dto.request.LoginRequest;
import com.yohuang.bookrental.dto.response.*;
import com.yohuang.bookrental.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse login(LoginRequest request) {
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return toUserResponse(user);
    }

    public UserResponse getUserById(String id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(AppUser user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setInventories(
                inventoryRepository.findByUserId(user.getId()).stream()
                        .map(this::toInventoryBookResponse)
                        .collect(Collectors.toList()));
        return response;
    }

    private InventoryBookResponse toInventoryBookResponse(Inventory inventory) {
        InventoryBookResponse response = new InventoryBookResponse();
        response.setId(inventory.getId());
        response.setLoanDate(inventory.getLoanDate());
        response.setBook(toSimpleBookResponse(inventory.getBook()));
        return response;
    }

    private BookResponse toBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setImage(book.getImage());
        return response;
    }

    private SimpleBookResponse toSimpleBookResponse(Book book) {
        SimpleBookResponse response = new SimpleBookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setImage(book.getImage());
        return response;
    }
}
