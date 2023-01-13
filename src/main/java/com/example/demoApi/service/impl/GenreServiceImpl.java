package com.example.demoApi.service.impl;

import com.example.demoApi.builder.GenreBuilder;
import com.example.demoApi.dto.Response.GenreDto;
import com.example.demoApi.dto.request.CreateGenreDto;
import com.example.demoApi.repository.GenreRepository;
import com.example.demoApi.service.GenreService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDto createGenre(CreateGenreDto createGenreDto) {
        var genre = GenreBuilder.genreDtoToGenre(createGenreDto);
        var genreOpt = genreRepository.save(genre);
        return GenreBuilder.genreToGenreDto(genreOpt);
    }

    @Override
    public void deleteGenreById(Long id) {
        var genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр не найден");
        }
        genreRepository.deleteById(id);

    }

    @Override
    public GenreDto getGenreById(Long id) {
        var genreOpt = genreRepository.findById(id);
        if (genreOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр не найден");
        }
        return GenreBuilder.genreToGenreDto(genreOpt.get());
    }

    @Override
    public GenreDto updateGenreById(Long id, CreateGenreDto createGenreDto) {
        var genreOpt = genreRepository.findById(id);
        if (genreOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр не найден");
        }
        var genre = genreOpt.get();
        if (createGenreDto.getDescription() != null) {
            genre.setDescription(createGenreDto.getDescription());
        }
        if (createGenreDto.getName() != null) {
            genre.setName(createGenreDto.getName());
        }
        return GenreBuilder.genreToGenreDto(genreRepository.save(genre));
    }

    @Override
    public List<GenreDto> getAllGenres(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var genreOpt = genreRepository.findAll(pageable);
        if (genreOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр не найден");
        }
        return genreOpt
                .stream()
                .map(GenreBuilder::genreToGenreDto)
                .toList();
    }
}




