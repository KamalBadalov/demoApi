package com.example.demoApi.builder;

import com.example.demoApi.domain.Genre;
import com.example.demoApi.dto.Response.GenreDto;
import com.example.demoApi.dto.request.CreateGenreDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class GenreBuilder {
    public static GenreDto genreToGenreDto(Genre genre) {
        var genreDto = new GenreDto();
        genreDto.setDescription(genre.getDescription());
        genreDto.setIdAuthor(genre.getId());
        genreDto.setName(genre.getName());
        genreDto.setCountContent(genreDto.getCountContent());
        genreDto.setCountContent(genre.getContentList().size());
        return genreDto;
    }
    public static List<GenreDto> genreToGenreDtoList(List<Genre> genres) {
        if (genres.isEmpty()) {
            return List.of();
        }
        return genres.stream()
                .map(GenreBuilder::genreToGenreDto)
                .toList();
    }

    public static Genre genreDtoToGenre(CreateGenreDto createGenreDto) {
        var genre = new Genre();
        genre.setName(createGenreDto.getName());
        genre.setDescription(createGenreDto.getDescription());
        return genre;

    }
}
