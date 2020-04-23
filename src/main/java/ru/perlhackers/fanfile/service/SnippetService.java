package ru.perlhackers.fanfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perlhackers.fanfile.entity.*;
import ru.perlhackers.fanfile.repository.FileRepository;
import ru.perlhackers.fanfile.repository.LanguageRepository;
import ru.perlhackers.fanfile.repository.SnippetRepository;
import ru.perlhackers.fanfile.util.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class SnippetService {
    @Autowired
    SnippetRepository snippetRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    LanguageRepository languageRepository;

    public Snippet getSnippetById(long snippetId) {
        return snippetRepository.findById(snippetId);
    }

    public void saveSnippet(Snippet snippet) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        if (snippet.getIsHide()) {
            snippet.setEncryptedKey(Security.oneWayConvert(snippet.getTitle()));
        };
        List<File> fileList = snippet.getFileList();
        snippet.setFileList(null);
        //ебучая джава пошла ты нахуй
        for (File file : fileList) {
            Snippet new_snippet = new Snippet();
            new_snippet.setId(snippet.getId());
            file.setSnippet(new_snippet);
        }
        snippetRepository.save(snippet);
        fileRepository.saveAll(fileList);
    }

    public void deleteSnippet(Snippet snippet) {
        snippetRepository.deleteById(snippet.getId());
    }

    public List<Snippet> getAllPublicSnippets() {
        return snippetRepository.findAllByIsHideOrderByCreatingDate(false);
    }
}
