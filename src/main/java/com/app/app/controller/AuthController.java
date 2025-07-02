package com.app.app.controller;

import com.app.app.entity.User;
import com.app.app.payload.JWTTokenDto;
import com.app.app.payload.LoginDto;
import com.app.app.payload.OTPDetails;
import com.app.app.repository.UserRepository;
import com.app.app.service.JWTService;
import com.app.app.service.OTPService;
import com.app.app.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final OTPService otpService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jtsService;

    @Autowired
    public AuthController(OTPService otpService, UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jtsService) {
        this.otpService = otpService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jtsService = jtsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody @NotNull User user) {

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<User> opEmailId = userRepository.findByEmailId(user.getEmailId());
        if (opEmailId.isPresent()) {
            return new ResponseEntity<>("Email ID already exists", HttpStatus.BAD_REQUEST);
        }

        // Encode password and save user
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/content-manager")
    public ResponseEntity<?> contentManager(@RequestBody @NotNull User user) {

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<User> opEmailId = userRepository.findByEmailId(user.getEmailId());
        if (opEmailId.isPresent()) {
            return new ResponseEntity<>("Email ID already exists", HttpStatus.BAD_REQUEST);
        }

        // Encode password and save user
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENT");
        userRepository.save(user);

        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/blog-manager")
    public ResponseEntity<?> blogManager(@RequestBody @NotNull User user) {

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<User> opEmailId = userRepository.findByEmailId(user.getEmailId());
        if (opEmailId.isPresent()) {
            return new ResponseEntity<>("Email ID already exists", HttpStatus.BAD_REQUEST);
        }

        // Encode password and save user
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOG");
        userRepository.save(user);

        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

//    @GetMapping("/message")
//    public String getMessage() {
//        return "Hello Sir, Welcome.";
//    }

    @PostMapping("/usersignin")
    public ResponseEntity<?> userSignIn(@RequestBody LoginDto dto) {
        String jwtToken = userService.verifyLogin(dto);
        if (jwtToken != null) {
            JWTTokenDto jwtTokenDto = new JWTTokenDto();
            jwtTokenDto.setToken(jwtToken);
            jwtTokenDto.setTokenType("JWT");
            return new ResponseEntity<>(jwtTokenDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid Token", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/message")
    public String giveMessage() {
        return "Hello User, Welcome.";
    }

    @PostMapping("/login-otp")
    public String genrateOTP(@RequestParam String mobile) {
        Optional<User> opUser = userRepository.findByMobile(mobile);
        if (opUser.isPresent()) {
            String otp = otpService.genrateOTP(mobile);
            return "OTP generated successfully. \nMobile - " + mobile + "\nOTP - " + otp;
        }
        return "User with this mobile is not EXISTS";
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestParam String mobile, @RequestParam String otp) {
        System.out.println("Received Mobile: " + mobile + ", OTP: " + otp);

        boolean status = otpService.validateOTP(mobile, otp);
        System.out.println("OTP Validation Status: " + status);

        if (status) {
            Optional<User> opUser = userRepository.findByMobile(mobile);

            if (opUser.isPresent()) {
                String jwtToken = jtsService.genrateToken(opUser.get().getUsername());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of("message", "JWT token generated", "token", jwtToken));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid OTP"));
    }



}