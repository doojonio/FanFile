package perlhackers.fanfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perlhackers.fanfile.Dto.FileDto;
import perlhackers.fanfile.Dto.SnippetDto;
import perlhackers.fanfile.entity.File;
import perlhackers.fanfile.entity.Language;
import perlhackers.fanfile.entity.Snippet;
import perlhackers.fanfile.entity.User;
import perlhackers.fanfile.repository.FileRepository;
import perlhackers.fanfile.repository.LanguageRepository;
import perlhackers.fanfile.repository.SnippetRepository;
import perlhackers.fanfile.repository.UserRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SnippetService {
    @Autowired
    private SnippetRepository snippetRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LanguageRepository languageRepository;

    public void createSnippet(@NotNull SnippetDto snippetDto) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        User user = null;
        if (snippetDto.getUserId() != 0) {
            user = userRepository.findById(snippetDto.getUserId());
        }
        else if (snippetDto.getUserLogin() != null) {
            user = userRepository.findByLogin(snippetDto.getUserLogin());
        }
        else {
            user = userRepository.findById(1);
        }

        Snippet newSnippet = new Snippet();
        newSnippet.setCreateTime(currentTimestamp);
        newSnippet.setIsPublic(snippetDto.getIsPublic());
        newSnippet.setTitle(snippetDto.getTitle());
        newSnippet.setUser(user);

        List<File> newFiles = new ArrayList<>();
        for (FileDto fileDto : snippetDto.getFiles()) {
            File newFile = new File();
            newFile.setChangeTime(currentTimestamp);
            newFile.setContent(fileDto.getContent());
            newFile.setLanguage(languageRepository.findByName(fileDto.getLanguage()));
            newFile.setSnippet(newSnippet);
            newFile.setTitle(fileDto.getTitle());

            newFiles.add(newFile);
        }

        newSnippet.setFiles(newFiles);
        String hash = String.valueOf(newSnippet.getTitle().hashCode())
                + String.valueOf(currentTimestamp.toString().hashCode());
        newSnippet.setHiddenLink(hash);

        snippetRepository.save(newSnippet);
    }
    public List<SnippetDto> getPublicSnippets() {
        List<Snippet> snippets = snippetRepository.findAllByIsPublicTrueOrderByCreateTimeDesc();
        List<SnippetDto> result = convertSnippets2Dto(snippets);
        return result;
    }
    public List<String> getLanguageNames() {
        List<Language> languages = languageRepository.findAll();
        List<String> languageNames = new ArrayList<>();

        for (Language language : languages) {
            languageNames.add(language.getName());
        }

        return languageNames;
    }
    public SnippetDto getSnippet(@NotNull @NotEmpty Long id) {
        return convertSnippet2Dto(snippetRepository.findById(id).get());
    }
    public SnippetDto getSnippet(@NotNull @NotEmpty String hiddenLink) {
        return convertSnippet2Dto(snippetRepository.findByHiddenLink(hiddenLink));
    }
    public static SnippetDto convertSnippet2Dto(Snippet snippet) {
        if (snippet == null) {
            return null;
        }

        SnippetDto snippetDto = new SnippetDto();
        snippetDto.setId(snippet.getId());
        snippetDto.setTitle(snippet.getTitle());
        snippetDto.setCreateTime(snippet.getCreateTime());
        snippetDto.setHiddenLink(snippet.getHiddenLink());
        snippetDto.setIsPublic(snippet.getIsPublic());
        snippetDto.setUserId(snippet.getUser().getId());
        snippetDto.setUserLogin(snippet.getUser().getLogin());

        if (snippet.getFiles() != null) {
            List<FileDto> filesDto = new ArrayList<>();
            for (File file : snippet.getFiles()) {
                FileDto fileDto = new FileDto();
                fileDto.setId(file.getId());
                fileDto.setChangeTime(file.getChangeTime());
                fileDto.setContent(file.getContent());
                fileDto.setLanguage(file.getLanguage().getName());
                fileDto.setSnippetId(file.getSnippet().getId());
                fileDto.setTitle(file.getTitle());

                filesDto.add(fileDto);
            }

            snippetDto.setFiles(filesDto);
        }

        return snippetDto;
    }
    public static List<SnippetDto> convertSnippets2Dto(List<Snippet> snippets) {
        List<SnippetDto> snippetsDto = new ArrayList<>();
        for (Snippet snippet : snippets) {
            snippetsDto.add(convertSnippet2Dto(snippet));
        }

        return snippetsDto;
    }
}
