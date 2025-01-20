package com.example.board.author.controller;


import com.example.board.author.domain.Author;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //회원목록조회
    @GetMapping("/list")
    public List<AuthorListRes> authorList(){
        return authorService.findAll();
    }


    //회원가입
    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq dto) {
        authorService.save(dto);
        return "OK";
    }
}
