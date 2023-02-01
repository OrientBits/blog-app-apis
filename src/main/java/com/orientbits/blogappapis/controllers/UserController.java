package com.orientbits.blogappapis.controllers;

import com.orientbits.blogappapis.exceptions.APIResponse;
import com.orientbits.blogappapis.payloads.UserDto;
import com.orientbits.blogappapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDTO){
        UserDto createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDTO, @PathVariable Integer id){
        System.out.println(userDTO +" : "+id);
        UserDto updateUser = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(updateUser,HttpStatus.ACCEPTED);
    }

    //DELETE - delete user
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
            return new ResponseEntity<>(new APIResponse("User deleted Successfully",true),HttpStatus.OK);
    }

    //GET - get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id){
        UserDto userById = userService.getUserById(id);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    //GET - get all user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }


}
