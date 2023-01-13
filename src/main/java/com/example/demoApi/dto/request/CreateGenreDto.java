package com.example.demoApi.dto.request;

public class CreateGenreDto {
    String name;
    String description;

    public CreateGenreDto() {
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
