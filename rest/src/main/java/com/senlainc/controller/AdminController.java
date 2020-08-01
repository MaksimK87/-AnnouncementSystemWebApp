package com.senlainc.controller;

import com.senlainc.exceptions.RecordNotFoundException;
import com.senlainc.exceptions.SuchUserExistsException;
import com.senlainc.entity.Role;
import com.senlainc.user.UserService;
import com.senlainc.transfer.dto.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    ResponseEntity<List<UserDTO>> getAllUsers() {
        logger.info("Get all users");
        return ResponseEntity.ok(userService.allUsers());
    }

    @PostMapping("/admin/users")
    ResponseEntity<UserDTO> saveAdmin(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        logger.info("Save new admin: " + userDTO);
        UserDTO user = null;
        try {
            user = userService.saveUser(userDTO);
        } catch (SuchUserExistsException e) {
            e.printStackTrace();
        }
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(userDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/admin/users")
    ResponseEntity<UserDTO> updateAdmin(@RequestBody UserDTO userDTO) {

        if (userDTO.getRoles().contains(new Role(2L, "ROLE_ADMIN"))) {
            userService.update(userDTO);
            UserDTO user = userService.findUserById(userDTO.getId());
            logger.info("Updated user: " + user);

            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/admin/users/{id}")
    ResponseEntity<UserDTO> getAdmin(@PathVariable Long id) {
        logger.info("Find user by id: " + id);
        return new ResponseEntity(userService.findUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        logger.info("Delete user with id: " + id);
        if (userService.deleteUser(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new RecordNotFoundException("User id: " + id + " doesn't exist");
    }

}






























































/*
package com.senlainc.controller;

import com.senlainc.entity.User;
import com.senlainc.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

}
*/
