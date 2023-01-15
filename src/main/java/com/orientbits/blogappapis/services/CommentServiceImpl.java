package com.orientbits.blogappapis.services;

import com.orientbits.blogappapis.entities.Comment;
import com.orientbits.blogappapis.entities.Post;
import com.orientbits.blogappapis.exceptions.ResourceNotFoundException;
import com.orientbits.blogappapis.payloads.CommentDto;
import com.orientbits.blogappapis.repositories.CommentRepository;
import com.orientbits.blogappapis.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment id", commentId));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment id", commentId));
        comment.setContent(commentDto.getContent());
        Comment save = commentRepository.save(comment);
        return modelMapper.map(save,CommentDto.class);
    }
}
