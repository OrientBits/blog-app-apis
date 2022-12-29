package com.orientbits.blogappapis.controllers;

import com.orientbits.blogappapis.payloads.UserDTO;
import com.orientbits.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST - create user
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable("id") int id){
        System.out.println(userDTO +" : "+id);
        UserDTO updateUser = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(updateUser,HttpStatus.ACCEPTED);
    }

    //DELETE - delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id){
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    //GET - get user by id
    @GetMapping("get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id){
        UserDTO userById = userService.getUserById(id);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    //GET - get all user
    @GetMapping("getall")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }


}
