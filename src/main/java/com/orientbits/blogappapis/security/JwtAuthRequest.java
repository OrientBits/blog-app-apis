package com.orientbits.blogappapis.security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JwtAuthRequest {
    private String username;
    private String password;
}
