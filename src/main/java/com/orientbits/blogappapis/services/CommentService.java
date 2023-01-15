package com.orientbits.blogappapis.services;

import com.orientbits.blogappapis.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);

    CommentDto updateComment(CommentDto commentDto, Integer commentId);
}
