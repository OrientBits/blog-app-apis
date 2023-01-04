package com.orientbits.blogappapis.controllers;

import com.orientbits.blogappapis.payloads.PostDto;
import com.orientbits.blogappapis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    //create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createdPost = postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //update post
    @PutMapping("/")
    public void updatePost(){

    }



}
