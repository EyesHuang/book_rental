package com.yohuang.bookrental.controller;

import com.yohuang.bookrental.dto.request.BorrowRequest;
import com.yohuang.bookrental.dto.response.BookResponse;
import com.yohuang.bookrental.dto.response.ErrorResponse;
import com.yohuang.bookrental.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<BookResponse>> getBooks(
            @Parameter(description = "offset", example = "0")
            @RequestParam(defaultValue = "0") int offset,
            @Parameter(description = "limit", example = "10")
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(bookService.getAllBooks(offset, limit));
    }

    @Operation(summary = "Get a book by Id")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borrow a book")
    @PutMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            bookService.borrowBook(request);
            return ResponseEntity.accepted().build();
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @Operation(summary = "Return a book")
    @PutMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRequest request) {
        try {
            bookService.returnBook(request);
            return ResponseEntity.accepted().build();
        } catch (RuntimeException e) {
            ErrorResponse error = new ErrorResponse();
            error.setErrors(List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }
}
