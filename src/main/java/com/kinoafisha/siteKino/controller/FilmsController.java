package com.kinoafisha.siteKino.controller;

import com.kinoafisha.siteKino.model.dto.FilmFullDto;
import com.kinoafisha.siteKino.model.dto.FilmsShortDto;
import com.kinoafisha.siteKino.repository.CommentsRepository;
import com.kinoafisha.siteKino.repository.FilmRepository;
import com.kinoafisha.siteKino.service.FilmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmRepository filmRepository;
    private final CommentsRepository commentsRepository;

    private final FilmsService filmsService;

    @GetMapping("/easy")
    public String simpleReq(){
        return "easy";
    }





    @GetMapping("/all")
    public String getAll(Model model)
    {
        List<FilmsShortDto> filmsShortDtos = filmsService.getAllFilmsSortDto();
        model.addAttribute("allFilms", filmsShortDtos);
        return "all_films";
    }







    @GetMapping("/{filmId}")
    public FilmFullDto getFilmByFilmId(@PathVariable Integer filmId){

        return filmsService.getFilmFullDtoById(filmId);

    }

    @GetMapping("/filmPage/{name}")
    public FilmFullDto getFilmByFilmName(@PathVariable String name){
        return filmsService.getFilmFullDtoByName(name);

    }
}
