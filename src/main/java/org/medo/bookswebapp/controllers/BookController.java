package org.medo.bookswebapp.controllers;

import org.medo.bookswebapp.domain.dtos.BookDto;
import org.medo.bookswebapp.domain.entities.BookEntity;
import org.medo.bookswebapp.mappers.impl.BookMapperImpl;
import org.medo.bookswebapp.services.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class BookController {

    private BookMapperImpl bookMapper;
    private BookServiceImpl bookService;

    public BookController(BookMapperImpl bookMapper, BookServiceImpl bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> putBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity newBook    = bookService.put(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(newBook), HttpStatus.OK);
    }


    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity newBook    = bookService.create(bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(newBook), HttpStatus.CREATED);
        // Problems:
            // repeated isbn !!
            // Author_id
    }

    @GetMapping("/books")
    public List<BookDto> showAllBooks() {
        List<BookEntity> booksEntities = bookService.showAll();
        return booksEntities.stream().map(bookMapper::mapTo).collect(Collectors.toList());

    }
}
