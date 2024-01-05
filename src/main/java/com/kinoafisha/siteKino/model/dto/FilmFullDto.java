package com.kinoafisha.siteKino.model.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class FilmFullDto {

    String name;

    String description;

    int year;

    Date releaseDate;

    String creator;

    String time; // продолжительность фильма

    String actors;

    String genre;

    String image;
    @Nullable
    Integer rating;

    @Nullable
    Integer myRating;

    List<CommentsShortDto> commentsShortDtos;
}