package perlhackers.fanfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import perlhackers.fanfile.Dto.UserDto;
import perlhackers.fanfile.entity.User;
import perlhackers.fanfile.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setSnippetAmount(user.getSnippets().size());
            usersDto.add(userDto);
        }

        return usersDto;
    }

    public UserDto registerUser(UserDto userDto) throws Exception {
        if (userRepository.existsUserByLogin(userDto.getLogin())) {
            throw new Exception("User with this login already exists");
        }

        User newUser = new User();
        newUser.setLogin(userDto.getLogin());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(newUser);
        User savedUser = userRepository.findByLogin(userDto.getLogin());
        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(savedUser.getId());
        savedUserDto.setLogin(savedUser.getLogin());
        return savedUserDto;
    }
    public UserDto getUserByLogin(String login) {
        User userFromDb = userRepository.findByLogin(login);
        if (userFromDb == null) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setId(userFromDb.getId());
        userDto.setLogin(userFromDb.getLogin());
        if (userFromDb.getSnippets() != null) {
            userDto.setSnippets(SnippetService.convertSnippets2Dto(userFromDb.getSnippets()));
        }

        return userDto;
    }

    public UserDto getUserById(long id) {
        User userFromDb = userRepository.findById(id);
        if (userFromDb == null) {
            return null;
        }
        UserDto userDto= new UserDto();

        userDto.setId(userFromDb.getId());
        userDto.setLogin(userFromDb.getLogin());
        if (userFromDb.getSnippets() != null) {
            userDto.setSnippets(SnippetService.convertSnippets2Dto(userFromDb.getSnippets()));
            userDto.setSnippetAmount(userDto.getSnippets().size());
        }

        return userDto;
    }

    public UserDto authUser(UserDto userDto) throws Exception {
        User userFromDb = userRepository.findByLogin(userDto.getLogin());
        if (userFromDb == null) {
            throw new Exception("No user with such login");
        }
        Boolean success = passwordEncoder.matches(userDto.getPassword(), userFromDb.getPassword());

        UserDto userDtoFromDb = new UserDto();
        if (success) {
            userDtoFromDb.setLogin(userFromDb.getLogin());
            userDtoFromDb.setId(userFromDb.getId());
        }

        return userDtoFromDb;
    }
}
