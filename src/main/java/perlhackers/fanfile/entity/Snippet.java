package perlhackers.fanfile.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "snippets")
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "snippet")
    private List<File> files;
    @Column(name = "public")
    @NotNull
    private Boolean isPublic = true;
    @Column(name = "title")
    @NotNull
    private String title = "Unnamed";
    @Column(name = "h_link")
    private String hiddenLink;
    @Column(name = "create_time")
    @NotNull
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());
}
