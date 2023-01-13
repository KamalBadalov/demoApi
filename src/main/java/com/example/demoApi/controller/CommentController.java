package com.example.demoApi.controller;

import com.example.demoApi.dto.Response.CommentDto;
import com.example.demoApi.dto.request.CreateCommentDto;
import com.example.demoApi.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentDto create(@RequestBody CreateCommentDto createCommentDto) {
        return commentService.createComment(createCommentDto);
    }

    @GetMapping("{id}")
    public CommentDto getById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PatchMapping("{id}")
    public CommentDto updateById(@RequestBody CreateCommentDto createCommentDto, @PathVariable Long id) {
        return commentService.updateCommentById(id, createCommentDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
    }

    @GetMapping("all")
    List<CommentDto> findAll(int page, int size) {
        return commentService.getAllComment(page, size);
    }

    @GetMapping("comment-by-text")
    List<CommentDto> findByText(@RequestParam String text, int page, int size) {
        return commentService.getCommentByText(text, page, size);
    }

}
