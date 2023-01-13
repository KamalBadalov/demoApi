package com.example.demoApi.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contents")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "file_path", nullable = false)
    private String filePath;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "content_genre", joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList;

    public Content() {
    }

    public Content(Long id, String name, String filePath, String description, Date createDate, User authorId, List<Genre> genreList) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.description = description;
        this.createDate = createDate;
        this.authorId = authorId;
        this.genreList = genreList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return filePath;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
