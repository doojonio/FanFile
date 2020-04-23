package ru.perlhackers.fanfile.entity;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {
    @Id
    @Column
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "snippet_id")
    Snippet snippet;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @Column
    private String title;
    @Column
    private String content;
    @Column(name = "queue_num")
    private short queueNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public short getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(short queueNumber) {
        this.queueNumber = queueNumber;
    }
}
