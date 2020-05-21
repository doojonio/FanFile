package perlhackers.fanfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perlhackers.fanfile.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLogin(String login);
    User findByLogin(String login);
    User findById(long id);
    List<User> findAll();
}
