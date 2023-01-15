package com.orientbits.blogappapis.entities.controllers;

import com.orientbits.blogappapis.payloads.APIResponse;
import com.orientbits.blogappapis.payloads.PostDto;
import com.orientbits.blogappapis.payloads.PostResponse;
import com.orientbits.blogappapis.services.FileService;
import com.orientbits.blogappapis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    //create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createdPost = postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //update post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto postDto1 = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts") //Integer pageNo, Integer pageSize
    public ResponseEntity<PostResponse> getPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                 @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                 @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = postService.getPost(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    //get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(
            @PathVariable Integer categoryId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        PostResponse postResponse = postService.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(
            @PathVariable Integer userId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        PostResponse postResponse = postService.getPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    // delete post by id
    @DeleteMapping("/post/{postId}")
    public APIResponse deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new APIResponse("Successfully Deleted", true);
    }


    //searching...
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
        List<PostDto> postDtos = postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }


    // post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId, @RequestParam("image")MultipartFile image) throws IOException {

        PostDto post = postService.getPost(postId);

        String fileName = fileService.uploadImage(path, image);

        System.out.println("FileName: "+fileName);

        post.setImageName(fileName);
        PostDto updatePost = postService.updatePost(post, postId);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);

    }

    //to serve files
    @GetMapping("/post/image/{imageName}")
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }



}
