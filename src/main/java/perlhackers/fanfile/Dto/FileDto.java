package perlhackers.fanfile.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class FileDto {
    @NotEmpty
    private long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private long snippetId;
    @NotEmpty
    private Timestamp changeTime;
    @NotEmpty
    private String language;
}
