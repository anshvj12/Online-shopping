package com.example.shopping.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InpUser {

    private int userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phone;

    private String role;

}
