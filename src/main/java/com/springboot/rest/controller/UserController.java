package com.springboot.rest.controller;

import com.springboot.rest.dto.UserDto;
import com.springboot.rest.repository.UserRepository;
import com.springboot.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/userinfo")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/alluser", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUserinfo() {
        LOGGER.info("inside class!!!! UserController, method!!!: getUserinfo");
        return userService.getAllUserInfo();
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.version-v1+json"})
    public UserDto getUserById(@PathVariable("userId") String userId) {
        LOGGER.info("inside method!!!: getUserById", userId);
        return userService.getUserByUserId(userId);
    }

    @DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserById(@PathVariable("userId") String userId) {
        userRepository.deleteById(userId);
    }

}
