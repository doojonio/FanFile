package perlhackers.fanfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import perlhackers.fanfile.Dto.SnippetDto;
import perlhackers.fanfile.response.BaseResponse;
import perlhackers.fanfile.service.SnippetService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/snippet")
public class SnippetController {
    @Autowired
    private SnippetService snippetService;

    @GetMapping("/languages")
    public List<String> getLanguageNames() {
        return snippetService.getLanguageNames();
    }

    @PostMapping
    public BaseResponse createSnippet(@RequestBody @NotNull SnippetDto snippetDto) {
        if (snippetDto.getFiles() == null) {
            return new BaseResponse("Fail", 400);
        }
        snippetService.createSnippet(snippetDto);
        return new BaseResponse("Success", 200);
    }
    @GetMapping("/public")
    public List<SnippetDto> getPublicSnippets() {
        return snippetService.getPublicSnippets();
    }
    @GetMapping
    public SnippetDto getSnippet(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "hlink", required = false) String hiddenLink) {
        SnippetDto snippetDto = null;
        if (id != null) {
            snippetDto = snippetService.getSnippet(id);
            if (!snippetDto.getIsPublic()) {
                snippetDto = null;
            }
        }
        if (hiddenLink != null) {
            snippetDto = snippetService.getSnippet(hiddenLink);
        }

        return snippetDto;
    }
}
