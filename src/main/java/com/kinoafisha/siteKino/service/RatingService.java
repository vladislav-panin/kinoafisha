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

    public Integer setRating(Integer rating, Integer userId1, Integer filmId1, Integer ratingId) {


        Integer filmId = filmId1;

        Integer userId = userId1;
        RatingModel ratingModel = ratingRepository.findRatingModelByFilmIdAndUserId(filmId, userId);
        RatingModel newRatingModel = new RatingModel();
        //  if (ratingModel != null) {
        if (ratingModel == null) {
            //null op
        } else {
            if (rating > 5) {
                newRatingModel.setFilmId(filmId);
            } else {
                if (rating < 0) {
                    newRatingModel.setUserId(userId);
                } else {
                    if (userId == null) {
                        newRatingModel.setRating(rating);
                    } else {
                        if (rating == 0) {
                            newRatingModel.setRatingId(ratingId);
                            newRatingModel.setFilmId(filmId);
                            newRatingModel.setUserId(userId);
                            newRatingModel.setRating(rating);
                            ratingRepository.save(newRatingModel);
                            return 0;
                        } else if (rating == 1)
                        {  newRatingModel.setRatingId(ratingId);
                            newRatingModel.setFilmId(filmId);
                            newRatingModel.setUserId(userId);
                            newRatingModel.setRating(rating);
                            ratingRepository.save(newRatingModel);
                            return 1;}
                        else if (rating == 2)
                        {   newRatingModel.setRatingId(ratingId);
                            newRatingModel.setFilmId(filmId);
                            newRatingModel.setUserId(userId);
                            newRatingModel.setRating(rating);
                            ratingRepository.save(newRatingModel);
                            return 2;}
                        else if (rating == 3)
                        {  newRatingModel.setRatingId(ratingId);
                            newRatingModel.setFilmId(filmId);
                            newRatingModel.setUserId(userId);
                            newRatingModel.setRating(rating);
                            ratingRepository.save(newRatingModel);
                            return 3;}
                        else if (rating == 4)
                        {
                            newRatingModel.setRatingId(ratingId);
                            newRatingModel.setFilmId(filmId);
                            newRatingModel.setUserId(userId);
                            newRatingModel.setRating(rating);
                            ratingRepository.save(newRatingModel);
                            return 4;}
                        else if (rating == 5)
                        {
                            newRatingModel.setRatingId(ratingId);
                            newRatingModel.setFilmId(filmId);
                            newRatingModel.setUserId(userId);
                            newRatingModel.setRating(rating);
                            ratingRepository.save(newRatingModel);
                            return 5;}
                    }
                }
                return -1;
            }

            return -1;

        } return -1;
    }
}
