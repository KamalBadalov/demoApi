package com.example.demoApi.builder;

import com.example.demoApi.domain.Content;
import com.example.demoApi.domain.Genre;
import com.example.demoApi.domain.User;
import com.example.demoApi.dto.Response.CommentDto;
import com.example.demoApi.dto.Response.ContentDto;
import com.example.demoApi.dto.Response.GenreDto;
import com.example.demoApi.dto.request.CreateContentDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ContentBuilder {
    public static ContentDto contentToContentDto(Content content, Long countComment,
                                                 List<GenreDto> genreList, CommentDto commentDto) {
        var contentDto = new ContentDto();
        if (countComment == null) {
            return null;
        }
        contentDto.setDescription(content.getDescription());
        contentDto.setName(content.getName());
        contentDto.setAuthorId(content.getAuthorId().getId());
        contentDto.setId(content.getId());
        contentDto.setPath(content.getFilePath());
        contentDto.setCountComments(countComment);
        contentDto.setGenreDtos(genreList);
        contentDto.setLastComment(commentDto);
        return contentDto;
    }

    public static Content contentDtoToContent(CreateContentDto createContentDto, User user, List<Genre> genres) {
        var content = new Content();
        content.setDescription(createContentDto.getDescription());
        content.setAuthorId(user);
        content.setName(createContentDto.getName());
        content.setFilePath(createContentDto.getFilePath());
        content.setCreateDate(new Date());
        content.setGenreList(genres);
        return content;

    }
}
