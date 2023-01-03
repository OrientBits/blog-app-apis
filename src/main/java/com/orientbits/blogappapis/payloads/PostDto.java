package com.orientbits.blogappapis.payloads;

import com.orientbits.blogappapis.entities.Category;
import com.orientbits.blogappapis.entities.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    @NotEmpty
    @Size(min = 10,max = 100)
    private  String title;

    @NotEmpty
    @Size(min = 10)
    private String content;

    private Date addedDate;

    private String imageName;

    private CategoryDto category;

    private UserDto user;

}
