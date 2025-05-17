package com.example.shopping.service;

import com.example.shopping.Model.Card;
import com.example.shopping.Model.Role;
import com.example.shopping.Model.User;
import com.example.shopping.dao.CardRepository;
import com.example.shopping.dao.RoleRepository;
import com.example.shopping.dao.UserRepository;
import com.example.shopping.dto.UserDTO;
import com.example.shopping.exception.BadRequestException;
import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.mapper.UserMapper;
import com.example.shopping.util.Constants;
import com.example.shopping.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final CardRepository cardRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Override
    public Optional<User> findUserById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }



    @Override
    @Transactional
    public User saveUser(UserDTO userDTO) {
        if(!userDTO.getPassword().equals(userDTO.getEnterAgainPassword()))
        {
            throw new BadRequestException(Constants.PASSWORD_MISMATCH+userDTO.getPassword());
        }

         userRepository.findByEmail(userDTO.getEmail()).
                ifPresent((User userByEmail) -> {throw new BadRequestException(Constants.EXIST_EMAIL+userDTO.getEmail());});
         userRepository.findByPhone(userDTO.getPhone()).
                ifPresent((User userByPhone) -> { throw new BadRequestException(Constants.EXIST_PHONENUMBER+userDTO.getPhone());});

        User user = UserMapper.maptoUser(userDTO,new User());
        user.setUserId(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRole(Roles.valueOf(userDTO.getRole()));
        role = roleRepository.save(role);
        user.getRoles().add(role);
        Card card = new Card();
        card.setUser(user);
        Card save = cardRepository.save(card);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND+email));
        UserDTO userDTO = new UserDTO();
        UserMapper.mapToUserDTO(user, userDTO);
        return Optional.of(userDTO);
    }
}
