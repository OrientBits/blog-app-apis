package com.orientbits.blogappapis;

import com.orientbits.blogappapis.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {

    }

    @Test
    public void repoTest(){
        System.out.println(userRepository.getClass().getName());
        System.out.println(userRepository.getClass().getPackageName());
    }

}
