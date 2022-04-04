package com.aksionav.bookservice.web;

import com.aksionav.bookservice.model.dto.BookRequest;
import com.aksionav.bookservice.model.dto.BookResponse;
import com.aksionav.bookservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public BookResponse createBook(@RequestBody @Validated BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Integer id, @RequestBody  BookRequest request) {
        return bookService.updateBook(id, request);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
