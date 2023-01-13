package com.example.demoApi.dto.Response;

public class GenreDto {
    Long idAuthor;
    String name;
    String description;
    Integer countContent;

    public GenreDto() {
    }

    public Integer getCountContent() {
        return countContent;
    }

    public void setCountContent(Integer countContent) {
        this.countContent = countContent;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
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
