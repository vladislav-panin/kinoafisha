package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.mapper.CommentsMapper;
import com.kinoafisha.siteKino.mapper.FilmsMapper;
import com.kinoafisha.siteKino.model.CommentsModel;
import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.model.dto.CommentsShortDto;
import com.kinoafisha.siteKino.model.dto.FilmFullDto;
import com.kinoafisha.siteKino.model.dto.FilmsShortDto;
import com.kinoafisha.siteKino.repository.CommentsRepository;
import com.kinoafisha.siteKino.repository.FilmRepository;
import com.kinoafisha.siteKino.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmsService {

    private final FilmRepository filmRepository;

    private final UsersRepository usersRepository;

    private final CommentsRepository commentsRepository;

    private final FilmsMapper filmsMapper;
    private final CommentsMapper commentsMapper;

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

    public CommentsModel addNewComment(CommentsModel commentsModel, UsersModel user)
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


}
