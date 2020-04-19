package ru.perlhackers.fanfile.repository;

import org.springframework.stereotype.Repository;
import ru.perlhackers.fanfile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   @Transactional
   User findById(long id);
}
