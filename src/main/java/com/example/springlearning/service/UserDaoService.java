package com.example.springlearning.service;

import com.example.springlearning.dto.LoginUserRequest;
import com.example.springlearning.exception.UserAlreadyExistException;
import com.example.springlearning.exception.UserNotFoundException;
import com.example.springlearning.model.User;
import com.example.springlearning.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDaoService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
//    private AuthenticationManager authenticationManager;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findUser(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("No user found with ID: " + id);
        return user.get();
    }

    public void saveUser(User user) throws UserAlreadyExistException {
        if(isUserAlreadyExist(user.getEmail())) throw new UserAlreadyExistException("User account with this email already exists");
        System.out.println(user.getEmail() + " " + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getEmail() + " " + user.getPassword());
        userRepository.save(user);
        System.out.println(user.getEmail() + " " + user.getPassword());
    }

    public boolean isUserAlreadyExist(String email) {
        return loadUserByUsername(email) != null;
    }

    public String login(LoginUserRequest loginReq) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "loggedIn";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(LoginUserRequest::new)
                .orElseThrow(() -> new BadCredentialsException("username or password is invalid"));
    }
}
