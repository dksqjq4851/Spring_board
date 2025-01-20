package com.example.board.post.controller;


import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostSaveReq;
import com.example.board.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    //글목록조회
    @GetMapping("/list")
    public List<PostListRes> postList(){
        return postService.findAll();
    }


    //글쓰기
    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq dto){
        postService.save(dto);
        return "OK";
    }



}
