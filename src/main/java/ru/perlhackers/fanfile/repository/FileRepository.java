package ru.perlhackers.fanfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perlhackers.fanfile.entity.File;
import ru.perlhackers.fanfile.entity.Snippet;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    @Transactional
    List<File> findAllBySnippet(Snippet snippet);
    @Transactional
    boolean existsBySnippetAndId(Snippet snippet, long id);
}
