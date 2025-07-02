package com.app.app.service;

import com.app.app.entity.User;
import com.app.app.payload.LoginDto;
import com.app.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, JWTService jwsService) {
        this.userRepository = userRepository;
        this.jwtService = jwsService;
    }

    public String verifyLogin(LoginDto dto){
        Optional<User> opUser = userRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if (BCrypt.checkpw(dto.getPassword(), user.getPassword())){
                return jwtService.genrateToken(user.getUsername());
            }
        }
        return "false";
    }
    public String validateOTP(LoginDto dto){
        Optional<User> opUser = userRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if (BCrypt.checkpw(dto.getPassword(), user.getPassword())){
                return jwtService.genrateToken(user.getUsername());
            }
        }
        return "false";
    }
}
