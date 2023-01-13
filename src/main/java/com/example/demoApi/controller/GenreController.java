package com.example.demoApi.controller;

import com.example.demoApi.dto.Response.GenreDto;
import com.example.demoApi.dto.request.CreateGenreDto;
import com.example.demoApi.service.GenreService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public GenreDto create(@RequestBody CreateGenreDto createGenreDto) {
        return genreService.createGenre(createGenreDto);
    }

    @GetMapping("{id}")
    public GenreDto getById(@PathVariable Long id) {
        return genreService.getGenreById(id);
    }

    @PatchMapping("{id}")
    public GenreDto updateById(@RequestBody CreateGenreDto createGenreDto, @PathVariable Long id) {
        return genreService.updateGenreById(id, createGenreDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        genreService.deleteGenreById(id);
    }

    @GetMapping
    public List<GenreDto> getAll(int page, @NotNull  int size) {
        return genreService.getAllGenres(page, size);
    }
}

