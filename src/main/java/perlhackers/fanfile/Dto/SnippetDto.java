package perlhackers.fanfile.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
public class SnippetDto {
    @NotEmpty
    private long id;
    @NotNull
    @NotEmpty
    private long userId = 1;
    @NotEmpty
    private String userLogin;
    @NotNull
    @NotEmpty
    private Boolean isPublic = true;
    @NotEmpty
    @NotNull
    private String title = "Unnamed snippet";
    @NotEmpty
    private String hiddenLink;
    @NotNull
    @NotEmpty
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    private List<FileDto> files;
}
