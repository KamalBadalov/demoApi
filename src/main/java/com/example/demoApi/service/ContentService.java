package com.example.demoApi.service;

import com.example.demoApi.dto.Response.ContentDto;
import com.example.demoApi.dto.request.CreateContentDto;

import java.util.List;

public interface ContentService {
    ContentDto createContent(CreateContentDto createContentDto);

    void deleteContentById(Long id);

    ContentDto getContentById(Long id);

    ContentDto updateContentById(Long id, CreateContentDto createContentDto);

    Long getContentSize();


    List<ContentDto> getContentByAuthorId(Long authorId, int pageNumber, int pageSize);

    List<ContentDto> getAllContents(int page, int size);

    boolean isCommentByContentExists(Long contentId);
}
