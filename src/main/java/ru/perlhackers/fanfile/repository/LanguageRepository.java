package ru.perlhackers.fanfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perlhackers.fanfile.entity.Language;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Short> {
    @Transactional
    List<Language> findAll();
}
