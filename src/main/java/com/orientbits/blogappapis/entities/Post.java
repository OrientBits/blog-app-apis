package com.orientbits.blogappapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private  String title;
    @Column(length = 1000)
    private String content;
    private String imageName;
    private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

}
