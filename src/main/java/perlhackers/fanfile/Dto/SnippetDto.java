package perlhackers.fanfile.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.List;

@Data
public class SnippetDto {
    @NotEmpty
    private long id;
    @NotEmpty
    private long userId = 1;
    @NotEmpty
    private String userLogin;
    @NotEmpty
    private Boolean isPublic = true;
    @NotEmpty
    private String title = "Unnamed snippet";
    @NotEmpty
    private String hiddenLink;
    @NotEmpty
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    private List<FileDto> files;
}
