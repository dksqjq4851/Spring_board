package com.example.board.post.controller;


import com.example.board.post.dtos.PostDetailRes;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostSaveReq;
import com.example.board.post.dtos.PostUpdateReq;
import com.example.board.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //글목록조회
    @GetMapping("/list")
    public String postList(Model model) {
        List<PostListRes> postListResList = postService.findAll();
        model.addAttribute("postList", postListResList);
        return "/post/post_list";

    }

    @GetMapping("/list/paging")
//    페이징처리를 위한 데이터 형식 : localhost:8080/post/list/paging?size=10&page=0&sort=createdTime,desc
    public String postListPaging(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("postList",postService.findAllPaging(pageable));
        return "post/post_list";
//        return postService.findAllPaging();
    }


    @GetMapping("/create")
    public String postCreate(){
        return "/post/post_create";
    }

    //글쓰기
    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq dto){
        postService.save(dto);
        return "redirect:/";
    }
    //글상세조회
    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model){
        PostDetailRes postDetailRes = postService.findById(id);
        model.addAttribute("post", postDetailRes);
        return "/post/post_detail";
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
