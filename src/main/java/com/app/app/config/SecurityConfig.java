package com.app.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/auth/signup", "api/v1/auth/usersignin").permitAll()  // Allows all requests without authentication
//                )
//                .csrf(csrf -> csrf.disable())  // Disable CSRF protection if necessary
//                .formLogin(login -> login.disable())  // Disable login form
//                .httpBasic(basic -> basic.disable()); // Disable basic authentication
//        http.addFilterBefore(jwtFilter, AuthenticationFilter.class);

    /// /        http.authorizeHttpRequests().requestMatchers("/api/v1/auth/signup", "api/v1/auth/signin").permitAll();
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/signup", "api/v1/auth/usersignin", "/api/v1/auth/content-manager", "/api/v1/auth/blog-manager", "/api/v1/auth/login-otp", "/api/v1/auth/verify-otp" ).permitAll()
                        .requestMatchers("/api/v1/cars/add",  "/api/v1/model/*", "/api/v1/brand/*", "/api/v1/fuel/*", "/api/v1/transmission/*", "/api/v1/year/*", "/api/v1/car/*", "/api/v1/images/*", "/api/v1/area/*","/api/v1/location/*", "/api/v1/agent/*","/api/v1/bulk-car/*",  "/api/v1/costumer/*").hasRole("CONTENT")
                        .requestMatchers("/api/v1/auth/message").hasAnyRole("CONTENT", "BLOG") // Allow listed URLs
                        .anyRequest().authenticated() // Authenticate other requests
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .formLogin(login -> login.disable())  // Disable login form
                .httpBasic(basic -> basic.disable()); // Disable basic authentication

        // Add JWT filter before authentication processing
        http.addFilterBefore(jwtFilter, AuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
