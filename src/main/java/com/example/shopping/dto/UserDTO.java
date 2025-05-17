package com.example.shopping.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class UserDTO {

    @NotEmpty(message = "First Name can not empty")
    private String firstName;

    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String enterAgainPassword;

    @Email(message = "Enter a valid email")
    @NotEmpty
    private String email;

    @NotEmpty
    //@Pattern(regexp = "$|[0-9]{10}", message = "Mobile number must be 10 digit and Number value only.")
    private String phone;

    @NotEmpty
    private String role;

}
