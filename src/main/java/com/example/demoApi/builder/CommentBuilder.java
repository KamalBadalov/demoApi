package com.example.demoApi.builder;

import com.example.demoApi.domain.Comment;
import com.example.demoApi.domain.Content;
import com.example.demoApi.domain.User;
import com.example.demoApi.dto.Response.CommentDto;
import com.example.demoApi.dto.request.CreateCommentDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentBuilder {
    public static CommentDto commentToCommentDto(Comment comment) {
        var commentDto = new CommentDto();
        if (comment == null) {
            return null;
        }
        commentDto.setText(comment.getText());
        commentDto.setId(comment.getId());
        commentDto.setAuthorId(comment.getAuthor().getId());
        commentDto.setContentId(comment.getContent().getId());
        return commentDto;
    }

    public static Comment commentDtoToComment(CreateCommentDto createCommentDto, User author, Content content) {
        var comment = new Comment();
        comment.setAuthor(author);
        comment.setCreateDate(new Date());
        comment.setContent(content);
        comment.setText(createCommentDto.getText());
        return comment;
    }

}
