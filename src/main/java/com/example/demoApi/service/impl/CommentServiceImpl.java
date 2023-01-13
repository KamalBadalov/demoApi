package com.example.demoApi.service.impl;

import com.example.demoApi.builder.CommentBuilder;
import com.example.demoApi.dto.Response.CommentDto;
import com.example.demoApi.dto.request.CreateCommentDto;
import com.example.demoApi.repository.CommentRepository;
import com.example.demoApi.repository.ContentRepository;
import com.example.demoApi.repository.UserRepository;
import com.example.demoApi.service.CommentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(ContentRepository contentRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDto createComment(CreateCommentDto createCommentDto) {
        var contentOpt = contentRepository.findById(createCommentDto.getContentId());
        var userOpt = userRepository.findById(createCommentDto.getAuthorId());
        if (contentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Контент не  найден");
        }
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Автор  не найден");
        }
        var commentSave = commentRepository.save(CommentBuilder.commentDtoToComment
                (createCommentDto,userOpt.get(),contentOpt.get()));
        return CommentBuilder.commentToCommentDto(commentSave);
    }

    @Override
    public void deleteCommentById(Long id) {
        var commentOpt = commentRepository.findById(id);
        if (commentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий  не найден");
        }

        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto getCommentById(Long id) {
        var commentOpt = commentRepository.findById(id);
        if (commentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий  не найден");
        }
        return CommentBuilder.commentToCommentDto(commentRepository.getById(id));
    }

    @Override
    public CommentDto updateCommentById(Long id, CreateCommentDto createCommentDto) {
        var commentOpt = commentRepository.findById(id);
        if (commentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий  не найден");
        }
        var comment = commentOpt.get();
        if (createCommentDto.getText() != null) {
            comment.setText(createCommentDto.getText());
        }
        var contentOpt = contentRepository.findById(createCommentDto.getContentId());
        contentOpt.ifPresent(comment::setContent);
        var userOpt = userRepository.findById(createCommentDto.getAuthorId());
        userOpt.ifPresent(comment::setAuthor);
        return CommentBuilder.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentByText(String text, int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var pageable = PageRequest.of(page, size);
        var comment = commentRepository.findCommentByText(text, pageable);
        if (comment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий  не найден");
        }
        return comment
                .stream()
                .map(CommentBuilder::commentToCommentDto)
                .toList();
    }

    @Override
    public List<CommentDto> getAllComment(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var pageable = PageRequest.of(page, size);
        return commentRepository.findAll(pageable)
                .stream()
                .map(CommentBuilder::commentToCommentDto)
                .toList();
    }
}
