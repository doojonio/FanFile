package ru.perlhackers.fanfile.service;

import org.apache.catalina.startup.SetAllPropertiesRule;
import org.springframework.beans.factory.annotation.Autowired;
import ru.perlhackers.fanfile.util.Security;
import ru.perlhackers.fanfile.entity.User;
import ru.perlhackers.fanfile.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) throws NoSuchAlgorithmException {
        String username = user.getUsername();
        user.setSalt(Security.oneWayConvert(username));
        String password = user.getPassword();
        password = Security.oneWayConvert(password) + user.getSalt();
        user.setPassword(password);
        userRepository.save(user);
    }

    public boolean authUser(User user) throws NoSuchAlgorithmException {
        boolean exists = userRepository.existsByUsername(user.getUsername());
        if (!exists) {
            return false;
        }
        User ourUser = userRepository.findByUsername(user.getUsername());
        String inputPassword = Security.oneWayConvert(user.getPassword()) + ourUser.getSalt();
        return ourUser.getPassword().equals(inputPassword);
    }

    public User getUserById(long id) {
        User user = userRepository.findById(id);
        return user;
    }
}
