package com.yohuang.bookrental.controller;

import com.yohuang.bookrental.dto.request.LoginRequest;
import com.yohuang.bookrental.dto.response.ErrorResponse;
import com.yohuang.bookrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(userService.login(request));
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(404).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(404).body(error);
        }
    }
}
