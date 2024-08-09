package com.devvictor.user_crud_spring.services;

import com.devvictor.user_crud_spring.dtos.auth.LoginUserDto;
import com.devvictor.user_crud_spring.dtos.auth.TokenResponseDto;
import com.devvictor.user_crud_spring.models.User;
import com.devvictor.user_crud_spring.repositories.UserRepository;
import com.devvictor.user_crud_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<TokenResponseDto> login(LoginUserDto dto) {
        try {
            String username = dto.username();
            String password = dto.password();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            User user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }

            TokenResponseDto response = jwtTokenProvider.createAccessToken(
                    username,
                    user.getRoles()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password supplied");
        }
    }
}
