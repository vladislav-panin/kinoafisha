package com.kinoafisha.siteKino.controller;

import com.kinoafisha.siteKino.model.CommentsModel;
import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.model.dto.FilmsShortDto;
import com.kinoafisha.siteKino.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("films")
@RequiredArgsConstructor
public class FilmsController {
    private final CommentsService commentsService;
    private final UsersService usersService;
    private final FilmsService filmsService;
    private final RatingService ratingService;
    private final FormingModelsForFilmsService formingModelsForFilmsService;

    @PostMapping("/addComment")
    public String addCommentToFilm(@ModelAttribute CommentsModel commentsModel, Model model)
    {
        UsersModel user = usersService.findAuthentificatedUser();
        model.addAttribute("comment", commentsService.saveComment(commentsModel, user));

        FilmModel filmModel = filmsService.getFilmModelByName(commentsModel.getFilmName());
        RatingModel ratingModel = ratingService.getRatingModel(filmModel.getFilmId(), user.getUserId());

        formingModelsForFilmsService.formingFilmPageModel(model,
                formingModelsForFilmsService.formingCommentsShortDtoListForFilm(commentsModel.getFilmName()),
                formingModelsForFilmsService.formingFilmFullDto(commentsModel.getFilmName()),
                formingModelsForFilmsService.formingRatingScale(),
                ratingModel.getRating());

        return "film_page";
    }
    @GetMapping("/films_all")
    public String getAll(Model model)
    {
        List<FilmsShortDto> filmsShortDtos = filmsService.getAllFilmsSortDto();
        model.addAttribute("films", filmsShortDtos);
        return "main_page";
    }

    @GetMapping("/filmPage/{name}")
    public String getFilmByFilmName(@ModelAttribute FilmModel filmModel,  Model model){

        formingModelsForFilmsService.formingFilmPageModel(
                model,
                formingModelsForFilmsService.formingCommentsShortDtoListForFilm(filmModel.getName()),
                formingModelsForFilmsService.formingFilmFullDto(filmModel.getName()),
                formingModelsForFilmsService.formingRatingScale(),
                formingModelsForFilmsService.formingUserRatingToFilmModel(filmModel));

        return "film_page";
    }
}
