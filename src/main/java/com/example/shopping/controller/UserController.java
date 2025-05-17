package com.example.shopping.controller;


import com.example.shopping.Model.User;
import com.example.shopping.dto.ResponseDTO;
import com.example.shopping.dto.UserDTO;
import com.example.shopping.exception.EntityNotCreated;
import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.service.UserService;
import com.example.shopping.util.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable
                                            @Email(message = "Enter a valid email") @NotEmpty String email) {
            UserDTO userByEmail = userService.findUserByEmail(email).orElseThrow(
                    () ->{ throw new ResourceNotFoundException(Constants.USER_NOT_FOUND+email);}
            );
            return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserDTO user) {

        User savedUser = userService.saveUser(user);
        if(savedUser != null)
        {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusCode(HttpStatus.CREATED.toString());
            responseDTO.setStatusMessage(Constants.USER_201);
            return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
        }
        else
        {
            throw new EntityNotCreated("User entity is not created");
        }
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userService.findAllUsers();
        return users;
    }

}
