package com.aksionav.bookservice.service;

import com.aksionav.bookservice.model.dto.BookRequest;
import com.aksionav.bookservice.model.dto.BookResponse;

import java.util.List;

public interface BookService {

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Integer id);

    BookResponse createBook(BookRequest request);

    BookResponse updateBook(Integer id, BookRequest request);

    void deleteBook(Integer id);
}
