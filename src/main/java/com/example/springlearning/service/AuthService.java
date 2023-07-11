package com.example.springlearning.service;

import com.example.springlearning.dto.LoginUserRequest;
import com.example.springlearning.exception.UserAlreadyExistException;
import com.example.springlearning.model.User;
import com.example.springlearning.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private AuthUtils authUtils;
    private PasswordEncoder passwordEncoder;
    private UserDaoService userDaoService;

    public String login(UserDetails loginReq) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authUtils.generateToken(loginReq);
    }

    public void register(User user) throws UserAlreadyExistException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDaoService.saveUser(user);
    }
}
