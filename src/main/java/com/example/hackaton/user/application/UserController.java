package com.example.hackaton.user.application;

import com.example.hackaton.user.domain.RequestUpdateUser;
import com.example.hackaton.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hackaton.user.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/put/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody RequestUpdateUser requestUpdateUser) {
        userService.updateUser(id, requestUpdateUser);
        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}