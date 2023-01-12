package com.orientbits.blogappapis.repositories;

import com.orientbits.blogappapis.entities.Category;
import com.orientbits.blogappapis.entities.Post;
import com.orientbits.blogappapis.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findByUser(Pageable pageable,User user);
    Page<Post> findByCategory(Pageable pageable, Category category);

    List<Post> findByTitleContaining(String title);

}
