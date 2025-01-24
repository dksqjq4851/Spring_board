package com.example.board.author.controller;


import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //회원목록조회
    @GetMapping("/list")
    public String authorList(Model model){
        List<AuthorListRes> authorListResList = authorService.findAll();
        model.addAttribute("authorList", authorListResList);
        return "author/author_list";
    }
    @GetMapping("/create")
    public String authorCreate(){
        return "/author/author_create";
    }
    @GetMapping("/login")
    public String authorLoginScreen(){
        return "/author/author_login";
    }


    //회원가입
    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq dto) {
        authorService.save(dto);
        return "redirect:/";
    }

    //회원상세조회
    @GetMapping("/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model) {
        AuthorDetailRes dto = authorService.findById(id);
        model.addAttribute("author", authorService.findById(id));
        return "/author/author_detail";
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
