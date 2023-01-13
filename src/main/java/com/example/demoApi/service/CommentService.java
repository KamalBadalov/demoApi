package com.example.demoApi.service;

import com.example.demoApi.dto.Response.CommentDto;
import com.example.demoApi.dto.request.CreateCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CreateCommentDto createCommentDto);

    void deleteCommentById(Long id);

    CommentDto getCommentById(Long id);

    CommentDto updateCommentById(Long id, CreateCommentDto createCommentDto);

    List<CommentDto> getCommentByText(String text, int page, int size);

    List<CommentDto> getAllComment(int page, int size);
}
