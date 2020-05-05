package perlhackers.fanfile.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull
    private Snippet snippet;
    @ManyToOne
    @NotNull
    private Language language;
    @Column(name = "title")
    @NotNull
    private String title = "Unnamed";
    @Column(name = "content")
    @NotNull
    private String content = "Empty content";
    @Column(name = "change_time")
    @NotNull
    private Timestamp changeTime = new Timestamp(System.currentTimeMillis());
}
