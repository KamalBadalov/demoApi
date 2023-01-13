package com.example.demoApi.service.impl;

import com.example.demoApi.builder.CommentBuilder;
import com.example.demoApi.builder.ContentBuilder;
import com.example.demoApi.builder.GenreBuilder;
import com.example.demoApi.dto.Response.ContentDto;
import com.example.demoApi.dto.request.CreateContentDto;
import com.example.demoApi.repository.CommentRepository;
import com.example.demoApi.repository.ContentRepository;
import com.example.demoApi.repository.GenreRepository;
import com.example.demoApi.repository.UserRepository;
import com.example.demoApi.service.ContentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final GenreRepository genreRepository;

    public ContentServiceImpl(ContentRepository contentRepository,
                              UserRepository userRepository, CommentRepository commentRepository,
                              GenreRepository genreRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public ContentDto createContent(CreateContentDto createContentDto) {
        var userOpt = userRepository.findById(createContentDto.getAuthorId());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Автор не найден");
        }
        var content = ContentBuilder.contentDtoToContent(createContentDto, userOpt.get(), List.of());
        if (createContentDto.getGenresIds() != null) {
            var genres = genreRepository.findByIdIn(createContentDto.getGenresIds());
            content = ContentBuilder.contentDtoToContent(createContentDto, userOpt.get(), genres);
        }
        var contentSave = contentRepository.save(content);
        return ContentBuilder.contentToContentDto
                (contentSave, commentRepository.findCountCommentByContentId(contentSave.getId()),
                        GenreBuilder.genreToGenreDtoList(content.getGenreList()), null);
    }

    @Override
    public void deleteContentById(Long id) {
        var contentOpt = contentRepository.findById(id).orElse(null);
        if (contentOpt == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Контент не найден");
        }
        contentRepository.deleteById(id);

    }

    @Override
    public ContentDto getContentById(Long id) {
        var contentOpt = contentRepository.findById(id);
        if (contentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Контент не найден");
        }
        var lastComment = commentRepository.findFirstByContentAndOrderByIdDesc(contentOpt.get().getId());
        return ContentBuilder.contentToContentDto(contentOpt.get(),
                commentRepository.findCountCommentByContentId(contentOpt.get().getId()),
                GenreBuilder.genreToGenreDtoList(contentOpt.get().getGenreList()),
                CommentBuilder.commentToCommentDto(lastComment));
    }


    @Override
    public ContentDto updateContentById(Long id, CreateContentDto createContentDto) {
        var contentOpt = contentRepository.findById(id);
        if (contentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var content = contentOpt.get();
        if (createContentDto.getFilePath() != null) {
            content.setFilePath(createContentDto.getFilePath());
        }

        if (createContentDto.getDescription() != null) {
            content.setDescription(createContentDto.getDescription());
        }
        if (createContentDto.getName() != null) {
            content.setName(createContentDto.getName());
        }
        if (createContentDto.getAuthorId() != null) {
            var userOpt = userRepository.findById(createContentDto.getAuthorId());
            if (userOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        if (!createContentDto.getGenresIds().isEmpty()) {
            content.setGenreList(createContentDto.getGenresIds()
                    .stream()
                    .map(genreRepository::getById)
                    .toList());
        }
        var lastComment = commentRepository.findFirstByContentAndOrderByIdDesc(content.getId());
        return ContentBuilder.contentToContentDto
                (content, commentRepository.findCountCommentByContentId(id),
                        GenreBuilder.genreToGenreDtoList(content.getGenreList()),
                        CommentBuilder.commentToCommentDto(lastComment));


    }

    @Override
    public Long getContentSize() {
        var content = contentRepository.findAll();
        if (content.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Контент не найден");
        }
        return (long) content.size();
    }

    @Override
    public List<ContentDto> getContentByAuthorId(Long authorId, int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var pageable = PageRequest.of(page, size);
        return contentRepository.findAllAuthorsId(authorId, pageable).stream()
                .map(content -> ContentBuilder.contentToContentDto(content, commentRepository.findCountCommentByContentId(content.getId()),
                        GenreBuilder.genreToGenreDtoList(content.getGenreList()),
                        CommentBuilder.commentToCommentDto(commentRepository.findFirstByContentAndOrderByIdDesc(content.getId())))
                )
                .toList();
    }


    @Override
    public List<ContentDto> getAllContents(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var pageable = PageRequest.of(page, size);
        return contentRepository.findAll(pageable).stream()
                .map(content -> ContentBuilder.contentToContentDto(content,
                        commentRepository.findCountCommentByContentId(content.getId()),
                        GenreBuilder.genreToGenreDtoList(content.getGenreList()),
                        CommentBuilder.commentToCommentDto
                                (commentRepository.findFirstByContentAndOrderByIdDesc(content.getId())))
                )
                .toList();
    }


    @Override
    public boolean isCommentByContentExists(Long contentId) {
        return contentRepository.isCommentByContentExists(contentId);
    }
}