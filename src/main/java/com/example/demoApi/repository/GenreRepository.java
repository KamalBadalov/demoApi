package com.example.demoApi.repository;

import com.example.demoApi.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByIdIn(List<Long> ids);

}
