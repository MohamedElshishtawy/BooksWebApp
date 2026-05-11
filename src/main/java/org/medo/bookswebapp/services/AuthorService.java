package org.medo.bookswebapp.services;

import org.medo.bookswebapp.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorEntity createAuthor(final AuthorEntity authorDto);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> show(Long $id);

    AuthorEntity update(Long id, AuthorEntity authorEntity);

    boolean isExist(Long id);

    AuthorEntity patchUpdate(Long id, AuthorEntity authorEntity);
}