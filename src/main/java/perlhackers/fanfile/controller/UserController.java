package perlhackers.fanfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import perlhackers.fanfile.Dto.UserDto;
import perlhackers.fanfile.response.BaseResponse;
import perlhackers.fanfile.service.UserService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static String SUCCESS = "success";
    private static String ERROR   = "error";
    private static int CODE_SUCCESS = 200;
    private static int CODE_ERROR   = 400;

    @PostMapping
    public BaseResponse registerUser (@RequestBody UserDto userDto) throws Exception {
        userService.registerUser(userDto);
        return new BaseResponse(SUCCESS, CODE_SUCCESS);
    }

    @GetMapping
    public UserDto getUser(@PathParam("login") String login,
                           @PathParam("id") Long id) {
        UserDto userDto = null;
        if (login != null) {
            userDto = userService.getUserByLogin(login);
        }
        else if (id != null) {
            userDto = userService.getUserById(id);
        }

        return userDto;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto) throws Exception {
        Boolean auth = userService.authUser(userDto);
        return auth ? userDto : null;
    }
}
