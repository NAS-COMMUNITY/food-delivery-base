package com.example.fooddelivery.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtSignUp {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
