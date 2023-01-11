package com.orientbits.blogappapis.payloads;

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

    private int id;

    @NotEmpty
    @Size(min = 10,max = 100)
    private  String title;

    @NotEmpty
    @Size(min = 10)
    private String content;

    private Date date;

    private String imageName;

    private CategoryDto category;

    private UserDto user;

}
