package com.idolconsultingfirm.controller;

import com.idolconsultingfirm.model.User;
import com.idolconsultingfirm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserRestController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        logger.info("Received request to add user: {}", user);
        User savedUser = userService.addUser(user);
        logger.info("User added successfully: {}", savedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable @NotEmpty String userId, @Valid @RequestBody User user) {
        logger.info("Received request to update user with ID: {}", userId);
        User updatedUser = userService.updateUser(userId, user);
        if (updatedUser != null) {
            logger.info("User updated successfully: {}", updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            logger.error("User not found with ID: {}", userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable @NotEmpty String userId) {
        logger.info("Received request to get user with ID: {}", userId);
        User user = userService.getUser(userId);
        if (user != null) {
            logger.info("User found: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            logger.error("User not found with ID: {}", userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NotEmpty String userId) {
        logger.info("Received request to delete user with ID: {}", userId);
        userService.deleteUser(userId);
        logger.info("User deleted successfully with ID: {}", userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
