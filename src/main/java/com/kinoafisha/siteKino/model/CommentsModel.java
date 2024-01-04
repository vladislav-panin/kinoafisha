package com.kinoafisha.siteKino.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments_table")
@Data
public class CommentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer commentsId;

    String name;

    String message;

    String image;

    String filmName;
}
