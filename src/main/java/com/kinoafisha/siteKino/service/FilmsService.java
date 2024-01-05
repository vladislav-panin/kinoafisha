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
    }

    public void saveFilmModel(FilmModel filmModel){

        filmRepository.save(filmModel);
    }
}
