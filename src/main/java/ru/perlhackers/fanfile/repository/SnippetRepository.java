package ru.perlhackers.fanfile.repository;

import org.springframework.stereotype.Repository;
import ru.perlhackers.fanfile.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.perlhackers.fanfile.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    @Transactional
    Snippet findById(long id);

    @Transactional
    List<Snippet> findAllByUser(User user);
}

