package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.mapper.CommentsMapper;
import com.kinoafisha.siteKino.model.CommentsModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.model.dto.CommentsShortDto;
import com.kinoafisha.siteKino.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;

    private final CommentsMapper commentsMapper;

    public CommentsModel saveComment(CommentsModel commentsModel, UsersModel user)
    {
        if(user != null)
        {
            String userName = user.getLogin();
            commentsModel.setName(userName);
            commentsRepository.save(commentsModel);
            return commentsModel;
        }
        else {
            System.out.println("вы не можете оставить комментарий, так как не авторизованы");
            return null;
        }
    }

    public List<CommentsModel> getAllCommentsToFilm(String filmName) {
        return commentsRepository.findCommentsModelByFilmName(filmName);
    }

    public CommentsShortDto toCommentsShortDto(CommentsModel commentsModel) {
        return commentsMapper.toCommentsShortDto(commentsModel);
    }
}
