package com.example.demoApi.repository;

import com.example.demoApi.domain.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(
            value = "select * from contents where author_id = :authorId",
            nativeQuery = true
    )
    Page<Content> findAllAuthorsId(@Param("authorId") Long authorId, Pageable pageable);

    @Query(
            value = """
                    select exists (select * from contents as cont,
                    comments as c where c.content_id = cont.id and  cont.id = :contentId)
                    """,
            nativeQuery = true
    )
    boolean isCommentByContentExists(@Param("contentId") Long contentId);
}