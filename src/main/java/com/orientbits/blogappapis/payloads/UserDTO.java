package com.orientbits.blogappapis.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;

    @NotEmpty
    @Size(min = 3, message = "Name must be min of 3 characters!")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 5,max = 10,message = "Password must be min of 3 chars!")
    private String password;
    @NotEmpty
    private String about;
}
