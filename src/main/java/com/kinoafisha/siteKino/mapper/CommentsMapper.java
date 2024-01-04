package com.kinoafisha.siteKino.mapper;

import com.kinoafisha.siteKino.model.CommentsModel;
import com.kinoafisha.siteKino.model.dto.CommentsShortDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentsMapper {
    private final ModelMapper modelMapper;

    public CommentsShortDto toCommentsShortDto(CommentsModel commentsModel){
        CommentsShortDto commentsShortDto = modelMapper.map(commentsModel, CommentsShortDto.class);
        return commentsShortDto;
    }

}
