package com.example.demoApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    @Column(name = "description", length = 255)
    private String description;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genreList")
    private List<Content> contentList;


    public Genre() {
    }

    public Genre(Long id, String name, String description, List<Content> contentList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contentList = contentList;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

