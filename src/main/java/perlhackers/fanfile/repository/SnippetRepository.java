package perlhackers.fanfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perlhackers.fanfile.entity.Snippet;

import java.util.List;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    List<Snippet> findAllByIsPublicTrueOrderByCreateTimeDesc();
    Snippet findByHiddenLink(String hiddenLink);
}
