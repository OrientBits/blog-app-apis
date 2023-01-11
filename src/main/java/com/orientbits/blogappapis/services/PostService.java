package com.orientbits.blogappapis.services;

import com.orientbits.blogappapis.entities.Category;
import com.orientbits.blogappapis.entities.Post;
import com.orientbits.blogappapis.entities.User;
import com.orientbits.blogappapis.payloads.PostDto;
import com.orientbits.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get single post
    PostDto getPost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNo, Integer pageSize,String soryBy);

    //get all post by category
    PostResponse getPostsByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);

    //get all post by user
    PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize);

    //search post
    List<PostDto> searchPosts(String keyword);

}
