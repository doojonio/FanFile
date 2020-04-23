package ru.perlhackers.fanfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.perlhackers.fanfile.controller.response.DefaultResponse;
import ru.perlhackers.fanfile.service.UserService;
import ru.perlhackers.fanfile.entity.User;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/api/user", method = RequestMethod.POST)
    public DefaultResponse createUser(@RequestBody User user) throws NoSuchAlgorithmException {
        User existedUser = userService.getUserByUsername(user.getUsername());
        if (existedUser != null) {
            return new DefaultResponse(false, "User with this username already exists");
        }
        userService.saveUser(user);
        return new DefaultResponse(true);
    }

    @RequestMapping(value="/api/login", method = RequestMethod.POST)
    public DefaultResponse login(@RequestBody User user) throws NoSuchAlgorithmException {
        boolean auth = userService.authUser(user);
        if (auth) {
            //TODO: sessions
            return new DefaultResponse(true);
        }

        return new DefaultResponse(false, "Failed authorization");
    }

    @RequestMapping("/api/user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        user.setPassword("");
        user.setSalt("");
        return user;
    }
}
