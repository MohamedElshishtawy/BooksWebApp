package org.medo.bookswebapp.services;

import org.medo.bookswebapp.domain.entities.BookEntity;

import java.util.List;

public interface BookService {

    public BookEntity create(BookEntity bookEntity);

    public BookEntity put(String isbn, BookEntity bookEntity);

    public List<BookEntity> showAll();
}
