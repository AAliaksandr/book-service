package com.aksionav.bookservice.service;

import com.aksionav.bookservice.exception.BookNotFoundException;
import com.aksionav.bookservice.model.dto.BookRequest;
import com.aksionav.bookservice.model.dto.BookResponse;
import com.aksionav.bookservice.model.entity.Author;
import com.aksionav.bookservice.model.entity.Book;
import com.aksionav.bookservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Integer id) {
        return bookRepository.findById(id)
                .map(book -> modelMapper.map(book, BookResponse.class))
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id %s is not found", id)));
    }

    @Override
    public BookResponse createBook(BookRequest request) {
        Author author = new Author();
        author.setName(request.getAuthorName());
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(request.getTitle());
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponse.class);
    }

    @Override
    public BookResponse updateBook(Integer id, BookRequest request) {
        Book savedBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id %s is not found", id)));
        Author author = new Author();
        author.setName(request.getAuthorName());
        author.setCode(savedBook.getAuthor().getCode());
        Book book = new Book();
        book.setCode(savedBook.getCode());
        book.setAuthor(author);
        book.setTitle(request.getTitle());
        Book updatedBook = bookRepository.save(book);
        return modelMapper.map(updatedBook, BookResponse.class);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
