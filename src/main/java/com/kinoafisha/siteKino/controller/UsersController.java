package com.kinoafisha.siteKino.controller;

import com.kinoafisha.siteKino.model.*;
import com.kinoafisha.siteKino.model.dto.*;
import com.kinoafisha.siteKino.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {


    private final UsersService usersService;

    private final FilmsService filmsService;

    private final RatingService ratingService;

    private final CommentsService commentsService;

    private final FormingModelService formingModelService;


    @GetMapping("/easy1")
    public String simpleReq(){
        return "easy";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @GetMapping("/logout")
    public String getLogoutPage(Model model){
        model.addAttribute("logoutRequest", new UsersModel());
        return "already_logged_in_error";
    }


    @GetMapping("/profile/{userName}")
    public String getOtherUserProfilePage(Model model,@PathVariable String userName){

        UsersModel userModel = usersService.getUserModel(userName);
        usersService.formingUserProfileModel(model, userModel);
        return "profile_other_user";
    }


    @GetMapping("/profile")
    public String getProfilePage(Model model){

        UsersModel usersModel = usersService.findAuthentificatedUser();
        usersService.formingUserProfileModel(model, usersModel);
        return "profile_page";
    }

    @GetMapping("/updateProfileGet")
    public String getUpdatePage(Model model){
        model.addAttribute("updateRequest", new UsersModel());
        return "update_profile";
    }


    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UsersModel usersModel)
    {
        usersService.profileUpdate(usersModel);
        return "profile_update_success";
    }


    @GetMapping("tryReg")
    public String tryRegister(){
        usersService.registerVariant2("velolog1", "Gagatun_1234");
        return "success";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel)
    {
        UsersModel registeredUser =  usersService.registerVariant1(usersModel.getLogin(), usersModel.getPassword());

        if(registeredUser != null){
            return "login_page";
        }else{
            return "reg_error_page";
        }
    }

    @PostMapping("/addComment")
    public String addCommentToFilm(@ModelAttribute CommentsModel commentsModel, Model model)
    {
        UsersModel user = usersService.findAuthentificatedUser();
        model.addAttribute("comment", commentsService.saveComment(commentsModel, user));

        FilmModel filmModel = filmsService.getFilmModelByName(commentsModel.getFilmName());
        RatingModel ratingModel = ratingService.getRatingModel(filmModel.getFilmId(), user.getUserId());

        filmsService.formingFilmPageModel(model,
                commentsService.formingCommentsShortDtoListForFilm(commentsModel.getFilmName()),
                filmsService.formingFilmFullDto(commentsModel.getFilmName()),
                ratingService.formingRatingScale(),
                ratingModel.getRating());

        return "film_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model)
    {
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            usersService.formingLoginModel(model, usersModel);
            return "main_page";
        }else {
            return "auth_error_page"; //
            }
    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute UsersModel usersModel, Model model)
    {
        usersService.logout();
        usersService.formingLogoutModel(usersModel, model);

        return "login_page";
    }

    @GetMapping("/films_all")
    public String getAll(Model model)
    {
        List<FilmsShortDto> filmsShortDtos = filmsService.getAllFilmsSortDto();
        model.addAttribute("films", filmsShortDtos);
        return "main_page";
    }

    @GetMapping("one_film/{filmId}")
    public String getFilmByFilmId(@PathVariable Integer filmId, @ModelAttribute FilmModel filmModel, Model model){

        FilmFullDto filmFullDto = filmsService.getFilmFullDtoById(filmId);

        return "film_page";

    }

    @PostMapping("/filmPage/setRating")
    public String setRating() {

        ratingService.setRating(2,1, 1, 3);
        return "success";
    }

    @GetMapping("/filmPage/{name}")
    public String getFilmByFilmName(@ModelAttribute FilmModel filmModel,  Model model){

        filmsService.formingFilmPageModel(model,
                     commentsService.formingCommentsShortDtoListForFilm(filmModel.getName()),
                     filmsService.formingFilmFullDto(filmModel.getName()),
                     ratingService.formingRatingScale(),
                     formingModelService.formingUserRatingToFilmModel(filmModel));

        return "film_page";
    }
}


