package perlhackers.fanfile.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDto {
    @NotEmpty
    private long id;
    @NotNull
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private List<SnippetDto> snippets;
}
