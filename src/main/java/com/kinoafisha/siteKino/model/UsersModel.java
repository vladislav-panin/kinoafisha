package com.kinoafisha.siteKino.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users_table")
@Data
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    String login;

    String password;

    Integer authentificated;

    String preferences;

    String birthDate;

    @Nullable
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    List<FilmModel> recomendedFilms;
}
