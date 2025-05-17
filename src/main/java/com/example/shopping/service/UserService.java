package com.example.shopping.service;

import com.example.shopping.Model.User;
import com.example.shopping.dto.UserDTO;
import com.example.shopping.request.InpReview;
import com.example.shopping.response.RespReview;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findUserById(Integer id);

    User saveUser(UserDTO user);

    List<User> findAllUsers();

    Optional<UserDTO> findUserByEmail(String email);
}
