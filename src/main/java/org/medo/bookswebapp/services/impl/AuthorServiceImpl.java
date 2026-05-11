package org.medo.bookswebapp.services.impl;

import org.medo.bookswebapp.domain.entities.AuthorEntity;
import org.medo.bookswebapp.repositories.AuthorRepository;
import org.medo.bookswebapp.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> show(Long $id) {
        return authorRepository.findById($id);
    }

    @Override
    public AuthorEntity update(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);
        return  authorRepository.save(authorEntity);
    }

    @Override
    public boolean isExist(Long id) {
        return show(id).isPresent();
    }

    @Override
    public AuthorEntity patchUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id); // to prevent changing the id of the user
        return  authorRepository.findById(id).map(existedAuthor -> {
           Optional.ofNullable(authorEntity.getName()).ifPresent(existedAuthor::setName);
           Optional.ofNullable(authorEntity.getAge()).ifPresent(existedAuthor::setAge);
            return authorRepository.save(existedAuthor);
        }).orElseThrow(() -> new RuntimeException("Author is not presented"));
    }
}
