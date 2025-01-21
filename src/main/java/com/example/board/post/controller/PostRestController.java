package com.example.board.post.controller;


import com.example.board.post.domain.Post;
import com.example.board.post.dtos.PostDetailRes;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostSaveReq;
import com.example.board.post.dtos.PostUpdateReq;
import com.example.board.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list/paging")
//    페이징처리를 위한 데이터 형식 : localhost:8080/post/list/paging?size=10&page=0&sort=createdTime,desc
    public Page<PostListRes> postListPaging(@PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println(pageable);
        return postService.findAllPaging(pageable);
//        return postService.findAllPaging();
    }



    //글쓰기
    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq dto){
        postService.save(dto);
        return "OK";
    }
    //글상세조회
    @GetMapping("/detail/{id}")
    public PostDetailRes postDetail(@PathVariable Long id){
        return postService.findById(id);
    }


    //글수정
    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, PostUpdateReq dto){
        postService.update(id, dto);
        return "OK";
    }



    //글삭제
    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.delete(id);
        return "OK";
    }



}
