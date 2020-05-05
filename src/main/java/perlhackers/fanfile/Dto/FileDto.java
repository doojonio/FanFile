package perlhackers.fanfile.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class FileDto {
    @NotEmpty
    private long id;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String content;
    @NotNull
    @NotEmpty
    private long snippetId;
    @NotEmpty
    private Timestamp changeTime;
    @NotNull
    @NotEmpty
    private String language;
}
