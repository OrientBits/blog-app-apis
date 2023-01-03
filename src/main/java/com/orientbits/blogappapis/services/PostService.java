package com.orientbits.blogappapis.services;

import com.orientbits.blogappapis.entities.Category;
import com.orientbits.blogappapis.entities.Post;
import com.orientbits.blogappapis.entities.User;
import com.orientbits.blogappapis.payloads.PostDto;
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
    List<PostDto> getAllPost();

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    //search post
    List<PostDto> searchPosts(String keyword);

}
