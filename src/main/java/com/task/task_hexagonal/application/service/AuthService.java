package com.task.task_hexagonal.application.service;

import com.task.task_hexagonal.domain.exception.InvalidCredentialsException;
import com.task.task_hexagonal.domain.exception.UserExistException;
import com.task.task_hexagonal.domain.input.AuthUseCase;
import com.task.task_hexagonal.domain.model.AuthRequest;
import com.task.task_hexagonal.domain.model.AuthResponse;
import com.task.task_hexagonal.domain.model.User;
import com.task.task_hexagonal.domain.output.UserRepository;
import com.task.task_hexagonal.infrastructure.input.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse authenticateUser(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.username(),
                            authRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
            String token = jwtService.generateToken(userDetails);

            return new AuthResponse(token);
        }catch (BadCredentialsException e){
            throw new InvalidCredentialsException("Usuario o contraseña incorrectos");

        }
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByUsername(user.username())) {
            throw new UserExistException("El nombre de usuario ya está en uso");
        }
        User userSave = new User(
                null,
                user.username(),
                passwordEncoder.encode(user.password()),
                user.name(),
                user.email(),
                user.rol()
        );
        return userRepository.save(userSave);
    }




}
