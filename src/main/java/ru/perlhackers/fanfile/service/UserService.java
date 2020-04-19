package ru.perlhackers.fanfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.perlhackers.fanfile.entity.User;
import ru.perlhackers.fanfile.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(long id) {
        User user = userRepository.findById(id);
        return user;
    }
}
