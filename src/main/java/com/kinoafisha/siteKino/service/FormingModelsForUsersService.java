package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.model.dto.FilmsShortDto;
import com.kinoafisha.siteKino.model.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormingModelsForUsersService {
    private final UsersService usersService;

    private final RatingService ratingService;

    private final FilmsService filmsService;
    public Model formingUserProfileModel(Model model, UsersModel usersModel) {

        UserProfileDto profile = usersService.getProfile(usersModel);
        List<RatingModel> ratingModelsWithHigherRate = ratingService.getByRatingRatingModelListForUser(5, usersModel.getUserId());
        List<FilmsShortDto> filmsShortDtoList;
        if (ratingModelsWithHigherRate.size() != 0) {
            filmsShortDtoList = new ArrayList<>();
            for (RatingModel ratingModel : ratingModelsWithHigherRate) {
                Integer filmId = ratingModel.getFilmId();
                FilmModel filmModel = filmsService.getFilmModelById(filmId);
                FilmsShortDto oneShortFilm = filmsService.getFilmsShortDto(filmModel);
                filmsShortDtoList.add(oneShortFilm);
            }
        } else {
            filmsShortDtoList = filmsService.getAllFilmsSortDto();
        }
        model.addAttribute("films", filmsShortDtoList);
        model.addAttribute("userLogin", usersModel.getLogin());
        model.addAttribute("profileRequest", profile);

        return model;
    }

    public Model formingLogoutModel(UsersModel authenticated, Model model) {
        model.addAttribute("userLogin", authenticated.getLogin());

        return model;
    }

    public Model formingLoginModel(Model model, UsersModel usersModel){

        usersService.loginUser(usersModel);
        model.addAttribute("films", filmsService.getAllFilmsSortDto());
        model.addAttribute("filmsNames", filmsService.getAllFilmsNamesForMainPage());
        model.addAttribute("userLogin", usersModel.getLogin());

        return model;
    }
}
