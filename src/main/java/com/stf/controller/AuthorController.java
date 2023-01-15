package com.stf.controller;

import com.stf.entity.Author;
import com.stf.entity.Wallet;
import com.stf.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@Valid Author author) {
        author.setSignDate(new Date());
        author.setWallet(new Wallet(0.0));
        Author createdAuthor = authorService.creatAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/authors/getAllAuthors")
    public ResponseEntity<?> findAllAuthors() {
        List<Author> authorList = authorService.findAllAuthor();
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/authors/{nickName}")
    public ResponseEntity<?> findByNickNameLick(@PathVariable String nickName) {
        List<Author> authorList = authorService.findByNickNameLick(nickName);
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/authors")//".../authors?page=1&size=2&sort=id,desc"可以这么写url来得到想要的页面也页面大小
    public ResponseEntity<?> findAuthorByPage(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Author> authors = authorService.findAll(pageable);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id){
        Author author = authorService.findAuthorById(id);
        if (author == null){
            return new ResponseEntity<>("Author with id " + id + " dose not exist",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<?> updateAuthorById(@PathVariable Long id,Author author){
        Author author1 = authorService.findAuthorById(id);
        if (author1 == null){
            return new ResponseEntity<>("Author with id " + id + " dose not exist",HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(author,author1);
        Author author2 = authorService.updateAuthor(author1);
        return new ResponseEntity<>(author2,HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> removeAuthorById(@PathVariable Long id){
        Author author = authorService.findAuthorById(id);
        if (author == null){
            return new ResponseEntity<>("Author with id " + id + " dose not exist",HttpStatus.BAD_REQUEST);
        }
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>("Author with id " + id + " removed",HttpStatus.NO_CONTENT);
    }

}
