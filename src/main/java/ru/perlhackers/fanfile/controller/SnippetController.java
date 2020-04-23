package ru.perlhackers.fanfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.perlhackers.fanfile.controller.response.DefaultResponse;
import ru.perlhackers.fanfile.entity.File;
import ru.perlhackers.fanfile.entity.Snippet;
import ru.perlhackers.fanfile.entity.User;
import ru.perlhackers.fanfile.service.SnippetService;
import ru.perlhackers.fanfile.service.UserService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class SnippetController {
    @Autowired
    SnippetService snippetService;
    @Autowired
    UserService userService;

    @RequestMapping(value="/api/snippet", method = RequestMethod.POST)
    public DefaultResponse createSnippet(@RequestBody Snippet snippet) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        if (snippet.getFileList() == null) {
            return new DefaultResponse(false, "Need fileList");
        }
        User user = userService.getUserByUsername(snippet.getUser().getUsername());
        snippet.setUser(user);
        snippetService.saveSnippet(snippet);
        return new DefaultResponse(true);
    }

    @GetMapping("/api/snippet/{snippetId}")
    public Snippet getSnippet(@PathVariable Long snippetId) {
        return snippetService.getSnippetById(snippetId);
    }

    @RequestMapping(value="/api/snippet/{snippetId}", method = RequestMethod.DELETE)
    public DefaultResponse deleteSnippet(@PathVariable Long snippetId) {
        Snippet snippet = snippetService.getSnippetById(snippetId);
        snippetService.deleteSnippet(snippet);
        return new DefaultResponse(true);
    }

    @GetMapping("/api/snippet")
    public List<Snippet> showSnippets() {
       return snippetService.getAllPublicSnippets();
    }
}
