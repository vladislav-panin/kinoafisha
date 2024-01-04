package com.kinoafisha.siteKino.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "films_table")
@Data
public class FilmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer filmId;

    String name;

    String description;

    int year;

    Date releaseDate;

    String creator;

    String time; // время сколько идет фильм

    String actors;

    String genre;

    String image;

    @Nullable
    Integer rating;

    @Nullable
    Integer myRating;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "filmId", referencedColumnName = "filmId")
    List<CommentsModel> comments;
}
