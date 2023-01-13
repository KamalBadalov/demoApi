package com.example.demoApi.controller;

import com.example.demoApi.dto.Response.ContentDto;
import com.example.demoApi.dto.request.CreateContentDto;
import com.example.demoApi.service.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ContentDto create(@RequestBody CreateContentDto createContentDto) {
        return contentService.createContent(createContentDto);
    }

    @GetMapping("{id}")
    public ContentDto getById(Long id) {
        return contentService.getContentById(id);
    }

    @GetMapping("size")
    public Long getContentSize() {
        return contentService.getContentSize();
    }

    @GetMapping("by-author-id")

    public List<ContentDto> getAllByAuthorId(Long authorId, int pageNumber, int pageSize) {
        return contentService.getContentByAuthorId(authorId, pageNumber, pageSize);
    }

    @GetMapping("has-comments")
    public boolean hasComments(Long id) {
        return contentService.isCommentByContentExists(id);
    }

    @PatchMapping("{id}")
    public ContentDto updateById(@RequestBody CreateContentDto createContentDto, @PathVariable Long id) {
        return contentService.updateContentById(id, createContentDto);
    }

    @GetMapping
    public List<ContentDto> getAll(int page, int size) {
        return contentService.getAllContents(page, size);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        contentService.deleteContentById(id);
    }
}

