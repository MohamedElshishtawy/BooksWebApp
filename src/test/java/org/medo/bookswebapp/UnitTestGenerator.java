package org.medo.bookswebapp;

import org.medo.bookswebapp.domain.dtos.AuthorDto;
import org.medo.bookswebapp.domain.dtos.BookDto;
import org.medo.bookswebapp.domain.entities.AuthorEntity;

public class UnitTestGenerator {

    public static AuthorEntity createAuthor(String name, int age) {
        return AuthorEntity.builder().name(name).age(age).build();
    }

    public static AuthorEntity createAuthor() {
        return createAuthor("Mohamed Unit", 22);
    }

    public static AuthorDto createAuthorDto() {
        return  AuthorDto.builder().name("Mohamed Dto Unit").age(99).build();
    }

    public static BookDto createBookDto() {
        return  BookDto.builder().isbn("123-dto").title("Dto Book").author(null).build();
    }
}
