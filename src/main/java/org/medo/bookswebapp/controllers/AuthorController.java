package org.medo.bookswebapp.controllers;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.medo.bookswebapp.domain.entities.AuthorEntity;
import org.medo.bookswebapp.mappers.Mapper;
import org.medo.bookswebapp.services.AuthorService;
import org.medo.bookswebapp.domain.dtos.AuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    private final Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }


    @PostMapping("/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity newUser = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/authors")
    public List<AuthorDto> showAuthors() {
        List<AuthorEntity> authorsEntities = authorService.findAll();
        return authorsEntities.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> showAuthor(@PathVariable Long id) {
        Optional<AuthorEntity> authorEntity = authorService.show(id);
        return authorEntity.map(authorEntity2 -> {
           AuthorDto authorDto = authorMapper.mapTo(authorEntity2);
           return new ResponseEntity<>(authorDto, HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
//        if (authorService.isExist(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthorEntity = authorService.update(id, authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthorEntity), HttpStatus.OK);
    }

//    @PatchMapping("/author/{id}")
//    public  ResponseEntity<AuthorDto> patchAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
//        if (authorService.isExist(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
//        AuthorEntity updatedAuthorEntity = authorService.patchUpdate(id, authorEntity);
//        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthorEntity), HttpStatus.OK);
//    }


}
