package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.mapper.RatingMapper;
import com.kinoafisha.siteKino.mapper.UsersMapper;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.dto.RatingDto;
import com.kinoafisha.siteKino.repository.RatingRepository;
import com.kinoafisha.siteKino.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingDto giveRatingToFilm(RatingModel ratingModel, Integer myRating){

        ratingModel.setRating(myRating);
        ratingRepository.save(ratingModel);
        RatingDto ratingDto = ratingMapper.toRatingDto(ratingModel);
        return ratingDto;

    }

    public Integer setRating(Integer rating, Integer userId, Integer filmId, Integer ratingId) {

        RatingModel ratingModel = ratingRepository.findRatingModelByFilmIdAndUserId(filmId, userId);

        if(!isRatingCorrect(rating, userId, ratingModel))
            return -1;

        saveRatingModel(rating,userId,filmId, ratingId);
        return rating;
    }

    public void saveRatingModel(Integer rating, Integer userId, Integer filmId, Integer ratingId){

        RatingModel updatedRatingModel = new RatingModel();
        updatedRatingModel.setRatingId(ratingId);
        updatedRatingModel.setFilmId(filmId);
        updatedRatingModel.setUserId(userId);
        updatedRatingModel.setRating(rating);
        ratingRepository.save(updatedRatingModel);
    }

    public boolean isRatingCorrect(Integer rating, Integer userId, RatingModel ratingModel){

        if(ratingModel == null || userId == null || rating > 5 || rating < 0)
            return false;

        return true;
    }
}
