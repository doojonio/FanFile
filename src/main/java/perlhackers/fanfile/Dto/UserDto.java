package perlhackers.fanfile.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UserDto {
    @NotEmpty
    private long id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private int snippetAmount;
    private List<SnippetDto> snippets;
}
