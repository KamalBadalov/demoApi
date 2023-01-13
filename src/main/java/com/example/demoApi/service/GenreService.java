package com.example.demoApi.service;

import com.example.demoApi.dto.Response.GenreDto;
import com.example.demoApi.dto.request.CreateGenreDto;

import java.util.List;

public interface GenreService {
    GenreDto createGenre(CreateGenreDto createGenreDto);

    void deleteGenreById(Long id);

    GenreDto getGenreById(Long id);

    GenreDto updateGenreById(Long id, CreateGenreDto createGenreDto);

    List<GenreDto> getAllGenres(int page, int size);
}
