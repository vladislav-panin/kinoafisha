package com.kinoafisha.siteKino.controller;

import com.kinoafisha.siteKino.mapper.FilmsMapper;
import com.kinoafisha.siteKino.model.*;
import com.kinoafisha.siteKino.model.dto.*;
import com.kinoafisha.siteKino.repository.*;
import com.kinoafisha.siteKino.service.CommentsService;
import com.kinoafisha.siteKino.service.FilmsService;
import com.kinoafisha.siteKino.service.RatingService;
import com.kinoafisha.siteKino.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {


    private final UsersService usersService;

    private final UsersRepository usersRepository;


    private final FilmsService filmsService;

    private final FilmRepository filmRepository;

    private final RatingService ratingService;

    private final FilmsMapper filmsMapper;

    private final CommentsService commentsService;





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

        UsersModel usersModel = usersRepository.findUsersModelByLogin(userName);
        formingUserProfileModel(model, usersModel);
        return "profile_other_user";
    }


    @GetMapping("/profile")
    public String getProfilePage(Model model){

        UsersModel usersModel = usersService.findAuthentificatedUser();
        formingUserProfileModel(model, usersModel);
        return "profile_page";
    }

    public Model formingUserProfileModel(Model model, UsersModel usersModel){

        UserProfileDto profile = usersService.getProfile(usersModel);
        List<RatingModel> ratingModelsWithHigherRate = ratingService.getByRatingRatingModelListForUser(5,usersModel.getUserId());
        List<FilmsShortDto> filmsShortDtoList;
        if(ratingModelsWithHigherRate.size()!=0)
        {
            filmsShortDtoList = new ArrayList<>();
            for(RatingModel ratingModel : ratingModelsWithHigherRate)
            {
                ratingModel.getRatingId();
                Integer filmId = ratingModel.getFilmId();
                FilmModel filmModel = filmRepository.findFilmModelByFilmId(filmId);
                FilmsShortDto oneShortFilm = filmsMapper.toFilmsShortDto(filmModel);
                filmsShortDtoList.add(oneShortFilm);

            }

        }
        else {
            filmsShortDtoList = filmsService.getAllFilms();
        }
        model.addAttribute("films", filmsShortDtoList);
        model.addAttribute("userLogin", usersModel.getLogin());
        model.addAttribute("profileRequest", profile);

        return model;
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
        usersService.registerUser1("velolog1", "Gagatun_1234");
        return "success";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel)
    {
        System.out.println("register request: " + usersModel);
        String isRegisterSucceed = usersService.registerUser1(usersModel.getLogin(), usersModel.getPassword());
        UsersModel registeredUser =  usersService.registerUser(usersModel.getLogin(), usersModel.getPassword());
        registeredUser.setPreferences("Нет предпочтений");
        registeredUser.setBirthDate("Не задана");
        registeredUser.setAuthentificated(0);
        usersRepository.save(registeredUser);

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

        FilmModel filmModel = filmRepository.findFilmModelByName(commentsModel.getFilmName());
        RatingModel ratingModel = ratingService.getRatingModel(filmModel.getFilmId(), user.getUserId());

        formingModel(model,
                commentsService.formingCommentsShortDtoListForFilm(commentsModel.getFilmName()),
                formingFilmFullDto(commentsModel.getFilmName()),
                ratingService.formingRatingScale(),
                ratingModel.getRating());

        return "film_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model)
    {

            System.out.println("login request: " + usersModel);
            UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());

            if (authenticated != null) {
                if(authenticated.getAuthentificated() != 1) {
                    authenticated.setAuthentificated(1);

                    usersRepository.save(authenticated);
                    model.addAttribute("userLogin", authenticated.getLogin());
                    List<FilmsShortDto> filmsShortDtos = filmsService.getAllFilms();
                    List<String> filmsNamesList = new ArrayList<>();
                    for(FilmsShortDto filmsShortDto : filmsShortDtos)
                    {
                        String filmName = filmsShortDto.getName();
                        filmsNamesList.add(filmName);
                    }
                    model.addAttribute("films", filmsShortDtos);
                    model.addAttribute("names",filmsNamesList);

                    return "main_page";
                }else {
                    return "already_logged_in_error";
                }
            } else {
                return "auth_error_page"; //
            }
    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute UsersModel usersModel, Model model)
    {
        System.out.println("logout request: " + usersModel);
        UsersModel authenticated = usersRepository.findUsersModelByAuthentificated(1);

        if (authenticated != null) {
            authenticated.setAuthentificated(0);

            usersRepository.save(authenticated);
            model.addAttribute("userLogin", authenticated.getLogin());
            return "login_page";
        } else {
            return "easy";
        }
    }

    @GetMapping("/films_all")
    public String getAll(Model model)
    {
        List<FilmsShortDto> filmsShortDtos = filmsService.getAllFilms();
        model.addAttribute("films", filmsShortDtos);
        return "main_page";
    }

    @GetMapping("one_film/{filmId}")
    public String getFilmByFilmId(@PathVariable Integer filmId, @ModelAttribute FilmModel filmModel, Model model){

        FilmFullDto filmFullDto = filmsService.findFilmById(filmId);

        return "film_page";

    }

    @PostMapping("/filmPage/setRating")
    public String setRating() {

        ratingService.setRating(2,1, 1, 3);
        return "success";
    }

    @GetMapping("/filmPage/{name}")
    public String getFilmByFilmName(@ModelAttribute FilmModel filmModel,  Model model){

        formingModel(model,
                     commentsService.formingCommentsShortDtoListForFilm(filmModel.getName()),
                     formingFilmFullDto(filmModel.getName()),
                     ratingService.formingRatingScale(),
                     ratingService.formingUserRatingToFilm(filmModel));

        return "film_page";
    }

    public FilmFullDto formingFilmFullDto(String filmName){

        FilmFullDto filmFullDto = filmsService.findFilmByNameDto(filmName);
        FilmModel filmModel = filmRepository.findFilmModelByName(filmName);

        Integer filmId = filmModel.getFilmId();
        List<RatingModel> ratingModelList = ratingService.getRatingModelList(filmId);

        Integer filmRating;
        Integer filmRatingSum = 0;
        if(ratingModelList.size() != 0) {
            for (RatingModel ratingModel : ratingModelList) {
                filmRatingSum = filmRatingSum + ratingModel.getRating();
            }
            Integer ratingListSize = ratingModelList.size();
            filmRating = filmRatingSum/ratingListSize;
            filmFullDto.setRating(filmRating);

            FilmModel filmModelForBd = filmRepository.findFilmModelByName(filmModel.getName());
            filmModelForBd.setRating(filmRating);
            filmRepository.save(filmModelForBd);

        } else {
            filmFullDto.setRating(0);
        }
        return filmFullDto;
    }

    public Model formingModel(Model model,
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

}


