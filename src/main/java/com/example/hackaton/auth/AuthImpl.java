package com.example.hackaton.auth;

import com.example.hackaton.user.domain.User;
import com.example.hackaton.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthImpl {
    @Autowired
    private UserService userService ;

    public boolean isOwnerResource(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findByEmail(username);

        // Comparar usando primitivos en lugar de equals
        if (user != null && user.getId() == id) {
            return true;
        }
        return false;
    }

    public String getCurrentEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}