package ru.perlhackers.fanfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.perlhackers.fanfile.service.UserService;
import ru.perlhackers.fanfile.entity.User;

import javax.websocket.server.PathParam;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/api/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        userService.saveUser(user);
        return "Success";
    }

    @RequestMapping("/api/user/{userId}")
    public User getUserById(@PathParam("userId") long userId) {
        User user = userService.getUserById(userId);
        return user;
    }
}
