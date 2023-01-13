package com.example.demoApi.dto.Response;

import java.util.List;

public class ContentDto {
    Long id;
    Long authorId;
    String name;
    String description;
    String Path;
    List<GenreDto> genreDtos;
    Long countComments;
    CommentDto lastComment;


    public ContentDto() {
    }

    public String getPath() {
        return Path;
    }

    public List<GenreDto> getGenreDtos() {
        return genreDtos;
    }

    public void setGenreDtos(List<GenreDto> genreDtos) {
        this.genreDtos = genreDtos;
    }

    public Long getCountComments() {
        return countComments;
    }

    public void setCountComments(Long countComments) {
        this.countComments = countComments;
    }

    public CommentDto getLastComment() {
        return lastComment;
    }

    public void setLastComment(CommentDto lastComment) {
        this.lastComment = lastComment;
    }

    public void setPath(String path) {
        Path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
