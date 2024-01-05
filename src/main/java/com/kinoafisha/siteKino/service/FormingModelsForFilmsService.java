package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.model.CommentsModel;
import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.model.dto.CommentsShortDto;
import com.kinoafisha.siteKino.model.dto.FilmFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormingModelsForFilmsService {
    private final UsersService usersService;

    private final CommentsService commentsService;

    private final RatingService ratingService;
    private final FilmsService filmsService;

    public List<Integer> formingRatingScale()
    {
        List<Integer> ratingScale = new ArrayList<>();
        for(int i=0; i<6; i++)
        {
            ratingScale.add(i);
        }
        return ratingScale;
    }
    public Integer formingUserRatingToFilmModel(FilmModel filmModel){

        UsersModel usersModel = usersService.findAuthentificatedUser();
        Integer userId = usersModel.getUserId();
        RatingModel ratingModel = ratingService.getRatingModel(filmModel.getFilmId(), userId);

        return ratingService.setRating(filmModel.getMyRating(),userId, filmModel.getFilmId(), ratingModel.getRatingId());
    }

    public List<CommentsShortDto> formingCommentsShortDtoListForFilm(String filmName){

        List<CommentsModel> commentsModelList = commentsService.getAllCommentsToFilm(filmName);
        List<CommentsShortDto> commentsShortDtoList = new ArrayList<>();

        if(commentsModelList.size()==0)
        {
            CommentsShortDto adminCommentShortDto = new CommentsShortDto();
            adminCommentShortDto.setName("admin");
            adminCommentShortDto.setMessage("Комментариев к фильму пока нет, будьте первым, кто оставит комментарий");
            commentsShortDtoList.add(adminCommentShortDto);

            return commentsShortDtoList;
        }
        for(CommentsModel one_comment: commentsModelList){
            CommentsShortDto one_shortComment = commentsService.toCommentsShortDto(one_comment);
            commentsShortDtoList.add(one_shortComment);
        }

        return commentsShortDtoList;
    }

    public Model formingFilmPageModel(Model model,
                                      List<CommentsShortDto> commentsShortDtoList,
                                      FilmFullDto filmFullDto,
                                      List<Integer> ratingScale,
                                      Integer userRating)
    {
        model.addAttribute("comments",commentsShortDtoList);
        model.addAttribute("film", filmFullDto);
        model.addAttribute("ratingScale", ratingScale);
        model.addAttribute("userRating", userRating);

        return model;
    }
    public FilmFullDto formingFilmFullDto(String filmName){

        FilmFullDto filmFullDto = filmsService.getFilmFullDtoByName(filmName);
        FilmModel filmModel = filmsService.getFilmModelByName(filmName);

        Integer filmId = filmModel.getFilmId();
        List<RatingModel> ratingModelList = ratingService.getRatingModelList(filmId);

        Integer filmRating;
        int filmRatingSum = 0;
        if(ratingModelList.size() != 0) {
            for (RatingModel ratingModel : ratingModelList) {
                filmRatingSum = filmRatingSum + ratingModel.getRating();
            }
            int ratingListSize = ratingModelList.size();
            filmRating = filmRatingSum/ratingListSize;
            filmFullDto.setRating(filmRating);

            FilmModel filmModelForBd = filmsService.getFilmModelByName(filmModel.getName());
            filmModelForBd.setRating(filmRating);
            filmsService.saveFilmModel(filmModelForBd);

        } else {
            filmFullDto.setRating(0);
        }
        return filmFullDto;
    }
}
