package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.mapper.FilmsMapper;
import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.dto.FilmFullDto;
import com.kinoafisha.siteKino.model.dto.FilmsShortDto;
import com.kinoafisha.siteKino.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmsService {

    private final FilmRepository filmRepository;

    private final FilmsMapper filmsMapper;


    public FilmFullDto findFilmById(Integer filmId){
        FilmModel filmModel = filmRepository.findFilmModelByFilmId(filmId);
        FilmFullDto filmDto = filmsMapper.toFilmFullDto(filmModel);
        return filmDto;
    }


    public FilmFullDto findFilmByNameDto(String name){
        FilmModel filmModel = filmRepository.findFilmModelByName(name);
        FilmFullDto filmDto = filmsMapper.toFilmFullDto(filmModel);
        return filmDto;
    }

    public FilmModel findFilmByNameModel(String name){
        FilmModel filmModel = filmRepository.findFilmModelByName(name);
        return filmModel;
    }

    public List<FilmsShortDto> getAllFilms(){
        List<FilmsShortDto> filmsShortDtos = new ArrayList<>();
        List<FilmModel> filmModels = filmRepository.findAll();
        for(FilmModel oneModel : filmModels)
        {
            FilmsShortDto shortDto = filmsMapper.toFilmsShortDto(oneModel);
            filmsShortDtos.add(shortDto);
        }
        return filmsShortDtos;
    }
}
