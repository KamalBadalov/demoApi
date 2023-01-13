package com.example.demoApi.dto.request;

import java.util.List;

public class CreateContentDto {
    String name;
    String filePath;
    String description;
    Long authorId;
    List<Long> genresIds;

    public CreateContentDto() {
    }

    public List<Long> getGenresIds() {
        return genresIds;
    }

    public void setGenresIds(List<Long> genresIds) {
        this.genresIds = genresIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
