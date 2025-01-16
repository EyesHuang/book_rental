package com.yohuang.bookrental.controller;

import com.yohuang.bookrental.dto.request.LoginRequest;
import com.yohuang.bookrental.dto.response.ErrorResponse;
import com.yohuang.bookrental.exception.NotFoundException;
import com.yohuang.bookrental.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(userService.login(request));
        } catch (IllegalArgumentException e) {
            // For invalid password
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        } catch (NotFoundException e) {
            // For user not found
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (RuntimeException e) {
            // For user not found
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @Operation(summary = "Get a user by Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            // Validate UUID format first
            UUID.fromString(id);
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (IllegalArgumentException e) {
            // UUID format error
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of("Invalid UUID format"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
