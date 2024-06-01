package com.example.hackaton.user.domain;

import com.example.hackaton.auth.AuthImpl;
import com.example.hackaton.exceptions.UnauthorizeOperationException;
import com.example.hackaton.user.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    AuthImpl authImpl;

    public User findByEmail(String username) {
        User user;
        user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Bean(name = "UserDetailsService")
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository
                    .findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return (UserDetails) user;
        };
    }

    public void updateUser(int id, RequestUpdateUser requestUpdateUser) {
        if (!authImpl.isOwnerResource(id))
            throw new UnauthorizeOperationException("Not allowed");

        User user = userRepository.findById((long) id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setName(requestUpdateUser.getName());
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
