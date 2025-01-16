package com.yohuang.bookrental.controller;

import com.yohuang.bookrental.dto.request.BorrowRequest;
import com.yohuang.bookrental.dto.response.ErrorResponse;
import com.yohuang.bookrental.entity.Book;
import com.yohuang.bookrental.exception.ConflictException;
import com.yohuang.bookrental.exception.NotFoundException;
import com.yohuang.bookrental.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(
            @ParameterObject Pageable page
    ) {
        return ResponseEntity.ok(bookService.findAll(page));
    }

    @Operation(summary = "Get a book by Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        try {
            // Validate UUID format first
            UUID.fromString(id);
            return ResponseEntity.ok(bookService.findById(id));
        } catch (IllegalArgumentException e) {
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

    @Operation(summary = "Borrow a book")
    @PutMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            // Validate UUID format first
            UUID.fromString(request.getUserId());
            UUID.fromString(request.getInventoryId());
            bookService.borrowBook(request);
            return ResponseEntity.accepted().build();
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of("Invalid UUID format"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (ConflictException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @Operation(summary = "Return a book")
    @PutMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRequest request) {
        try {
            // Validate UUID format first
            UUID.fromString(request.getUserId());
            UUID.fromString(request.getInventoryId());
            bookService.returnBook(request);
            return ResponseEntity.accepted().build();
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of("Invalid UUID format"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (NotFoundException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (ConflictException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
