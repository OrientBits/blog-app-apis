package com.orientbits.blogappapis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    int id;

    @NotEmpty
    @Size(min = 4,message = "Min size of category title is 4")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Min size of category description is 10")
    private String description;
}
