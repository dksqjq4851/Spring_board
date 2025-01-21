package com.example.board.author.controller;


import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //회원목록조회
    @GetMapping("/list")
    public String authorList(){
        List<AuthorListRes> authorListResList = authorService.findAll();
        return "author/author_list";
    }


    //회원가입
    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq dto) {
        authorService.save(dto);
        return "OK";
    }

    //회원상세조회
    @GetMapping("/detail/{id}")
    public AuthorDetailRes authorDetail(@PathVariable Long id){
        return authorService.findById(id);
    }

    //회원수정
    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, AuthorUpdateReq dto){
        authorService.update(id, dto);
        return "OK";
    }


    //회원삭제
    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.delete(id);
        return "OK";
    }
}
