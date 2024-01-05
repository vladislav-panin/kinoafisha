package com.kinoafisha.siteKino.service;


import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    private final UsersService usersService;

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

    public List<Integer> formingRatingScale()
    {
        List<Integer> ratingScale = new ArrayList<>();
        for(int i=0; i<6; i++)
        {
            ratingScale.add(i);
        }
        return ratingScale;
    }

    public Integer formingUserRatingToFilm(FilmModel filmModel){

        UsersModel usersModel = usersService.findAuthentificatedUser();
        Integer userId = usersModel.getUserId();
        RatingModel ratingModel = ratingRepository.findRatingModelByFilmIdAndUserId(filmModel.getFilmId(), userId);

        return setRating(filmModel.getMyRating(),userId, filmModel.getFilmId(), ratingModel.getRatingId());
    }

    public RatingModel getRatingModel(Integer filmId, Integer userId) {
       return ratingRepository.findRatingModelByFilmIdAndUserId(filmId, userId);
    }

    public List<RatingModel> getRatingModelList(Integer filmId)
    {
        return ratingRepository.findRatingModelsByFilmId(filmId);
    }

    public List<RatingModel> getByRatingRatingModelListForUser(Integer rating, Integer userId){
       return ratingRepository.findRatingModelsByRatingAndUserId(rating, userId);
    }
}
