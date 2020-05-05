package perlhackers.fanfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perlhackers.fanfile.entity.Language;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Short> {
    Language findByName(String name);
    List<Language> findAll();
}
