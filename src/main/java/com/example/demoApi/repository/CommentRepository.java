package com.example.demoApi.repository;

import com.example.demoApi.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(
            value = "select * from comments where  text like :%text",
            nativeQuery = true
    )
    Page<Comment> findCommentByText(@Param("text") String text, Pageable pageable);

    @Query(value = "select count(content_id) from comments where content_id = :contentId",
            nativeQuery = true)
    Long findCountCommentByContentId(@Param("contentId") Long contentId);

    @Query(
            value = """
                    select * from comments where content_id = :contentId
                    order by create_date desc limit 1
                    """,
            nativeQuery = true
    )
    Comment findFirstByContentAndOrderByIdDesc(@Param("contentId") Long contentId);
}

