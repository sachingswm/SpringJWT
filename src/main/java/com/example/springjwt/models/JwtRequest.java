package com.example.springjwt.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JwtRequest {
    private String username;
    private String password;
}
