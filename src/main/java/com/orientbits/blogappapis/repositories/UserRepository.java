package com.orientbits.blogappapis.repositories;

import com.orientbits.blogappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
