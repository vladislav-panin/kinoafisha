package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.mapper.FilmsMapper;
import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.dto.CommentsShortDto;
import com.kinoafisha.siteKino.model.dto.FilmFullDto;
import com.kinoafisha.siteKino.model.dto.FilmsShortDto;
import com.kinoafisha.siteKino.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmsService {

    private final FilmRepository filmRepository;

    private final FilmsMapper filmsMapper;

    private final RatingService ratingService;

    public FilmFullDto getFilmFullDtoById(Integer filmId){
        FilmModel filmModel = filmRepository.findFilmModelByFilmId(filmId);
        return filmsMapper.toFilmFullDto(filmModel);

    }


    public FilmFullDto getFilmFullDtoByName(String name){
        FilmModel filmModel = filmRepository.findFilmModelByName(name);
        return filmsMapper.toFilmFullDto(filmModel);
    }

    public FilmModel getFilmModelByName(String name){
        return filmRepository.findFilmModelByName(name);
    }

    public List<FilmsShortDto> getAllFilmsSortDto(){
        List<FilmsShortDto> filmsShortDtoList = new ArrayList<>();
        List<FilmModel> filmModels = filmRepository.findAll();
        for(FilmModel oneModel : filmModels)
        {
            FilmsShortDto shortDto = filmsMapper.toFilmsShortDto(oneModel);
            filmsShortDtoList.add(shortDto);
        }
        return filmsShortDtoList;
    }

    public FilmModel getFilmModelById(Integer filmId){
        return filmRepository.findFilmModelByFilmId(filmId);
    }

    public FilmFullDto formingFilmFullDto(String filmName){

        FilmFullDto filmFullDto = getFilmFullDtoByName(filmName);
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

    public FilmsShortDto getFilmsShortDto(FilmModel filmModel){
        return filmsMapper.toFilmsShortDto(filmModel);
    }

    public List<String> getAllFilmsNamesForMainPage() {
        List<FilmsShortDto> filmsShortDtoList = getAllFilmsSortDto();
        List<String> filmsNamesList = new ArrayList<>();
        for (FilmsShortDto filmsShortDto : filmsShortDtoList) {
            String filmName = filmsShortDto.getName();
            filmsNamesList.add(filmName);
        }
        return filmsNamesList;
        //  model.addAttribute("films", filmsShortDtoList);
        //  model.addAttribute("names",filmsNamesList);
    }
}
