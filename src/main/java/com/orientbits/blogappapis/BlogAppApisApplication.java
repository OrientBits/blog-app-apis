package com.orientbits.blogappapis;

import com.orientbits.blogappapis.entities.Role;
import com.orientbits.blogappapis.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            Role role = new Role();
            role.setId(501);
            role.setName("USER_ADMIN");

            Role role2 = new Role();
            role2.setId(502);
            role2.setName("USER_NORMAL");

            List<Role> roles = List.of(role, role2);

            List<Role> saveAll = roleRepository.saveAll(roles);

            saveAll.forEach(role1 -> System.out.println(role1.getName()));


        }catch (Exception e){

        }

    }
}