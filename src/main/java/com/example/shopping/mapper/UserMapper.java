package com.example.shopping.mapper;

import com.example.shopping.Model.User;
import com.example.shopping.dto.UserDTO;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user, UserDTO userDTO)
    {
        userDTO.setFirstName(user.getFirstName());

        if(user.getLastName() != null)
            userDTO.setLastName(user.getLastName());

        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        StringBuffer role = new StringBuffer();
        if(user.getRoles() != null) {
            user.getRoles().forEach(inp -> {
                role.append(inp.getRole());
                role.append(",");
            });
        }
        if(role.length() > 1)
        userDTO.setRole(role.substring(0,role.length()-1).toString());
        return userDTO;
    }

    public static User maptoUser(UserDTO userDTO, User user)
    {
        user.setFirstName(userDTO.getFirstName());

        if(userDTO.getLastName() != null)
            user.setLastName(userDTO.getLastName());

        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());

        return user;
    }

}
