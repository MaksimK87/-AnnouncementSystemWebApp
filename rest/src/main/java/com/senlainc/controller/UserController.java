package com.senlainc.controller;

import com.senlainc.user.UserService;
import com.senlainc.transfer.dto.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        logger.info("Get user id: " + id);
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/registration")
    ResponseEntity saveUser(@Valid @RequestBody UserDTO userDTO) {

        logger.info("Save new user: " + userDTO);

        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PutMapping("/users")
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {

        userService.update(userDTO);

        UserDTO user = userService.findUserById(userDTO.getId());
        logger.info("Updated user: " + user);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}/{rating}")
    ResponseEntity setUserRating(@PathVariable long id, @PathVariable int rating) {

        logger.info("Set rating for user id: " + rating);

        UserDTO user = userService.findUserById(id);
        user.setRating(rating);
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
