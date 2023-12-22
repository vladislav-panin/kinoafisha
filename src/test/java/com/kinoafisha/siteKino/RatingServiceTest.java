package com.kinoafisha.siteKino;

import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.repository.RatingRepository;
import com.kinoafisha.siteKino.service.RatingService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {


    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingService ratingService;

    //Структурированное базисное тестирование + анализ граничных значений + классы хороших данных
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating0(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(0);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 0;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);

        Assertions.assertEquals(0, actual);
    }

    //Структурированное базисное тестирование + классы хороших данных
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating2(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(2);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 2;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);


        Assertions.assertEquals(2, actual);
    }



    //Структурированное базисное тестирование + классы хороших данных
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating1(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(1);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 1;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);

        Assertions.assertEquals(1, actual);
    }

    //Структурированное базисное тестирование + классы хороших данных
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating3(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(3);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 3;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);

        Assertions.assertEquals(3, actual);
    }

    //Структурированное базисное тестирование + классы хороших данных
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating4(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(4);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 4;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);

        Assertions.assertEquals(4, actual);
    }

    //Структурированное базисное тестирование + анализ граничных значений + классы хороших данных
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating5(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(5);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 5;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);

        Assertions.assertEquals(5, actual);
    }

    //Структурное базисное тестирование
    @Test
    public void setRating_correctFilmId_unCorrectUserIdNull_correctRatingId_correctRating5(){
        Integer userId = null;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(null);
        ratingModel.setRating(5);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 5;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Угадывание ошибок
    @Test
    public void setRating_correctFilmId_correctUserId_unCorrectRatingId_correctRating5(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 15;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(15);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(5);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 5;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,times(1)).save(ratingModel);

        Assertions.assertEquals(5, actual);
    }

    //Анализ граничных условий
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_unCorrectRatingLowerBound(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(-1);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = -1;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Анализ граничных условий
    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_unCorrectRatingUpperBound(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(6);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 6;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Угадывание ошибок + классы плохих данных
    @Test
    public void setRating_unCorrectFilmId_correctUserId_correctRatingId_correctRating0(){
        Integer userId = 1;
        Integer filmId = 10;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3); //переписать под то, что вернется из таблицы
        ratingModel.setFilmId(10);
        ratingModel.setUserId(1);
        ratingModel.setRating(0);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 0;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }
    //Угадывание ошибок + классы плохих данных
    @Test
    public void setRating_сorrectFilmId_unCorrectUserId_correctRatingId_correctRating0(){
        Integer userId = 15;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(15);
        ratingModel.setRating(0);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 0;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Классы плохих данных + неполное тестирование
    @Test
    public void setRating_unCorrectFilmId_unCorrectUserId_correctRatingId_correctRating0(){
        Integer userId = 15;
        Integer filmId = 10;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(0);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 0;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Неполное тестирование + анализ граничных значений + класс плохих данных
    @Test
    public void setRating_unCorrectFilmId_unCorrectUserId_correctRatingId_unCorrectRatingUpperBound(){
        Integer userId = 15;
        Integer filmId = 10;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(6);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 6;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Неполное тестирование + анализ граничных значений + класс плохих данных
    @Test
    public void setRating_unCorrectFilmId_unCorrectUserId_correctRatingId_unCorrectRatingLowerBound(){
        Integer userId = 15;
        Integer filmId = 10;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(-2);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = -2;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Неполное тестирование + анализ граничных значений + класс плохих данных
    @Test
    public void setRating_unCorrectFilmId_unCorrectUserId_unCorrectRatingId_unCorrectRatingUpperBound(){
        Integer userId = 15;
        Integer filmId = 10;
        Integer ratingId = 15;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(15);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(6);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 6;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Неполное тестирование + анализ граничных значений + класс плохих данных
    @Test
    public void setRating_unCorrectFilmId_unCorrectUserId_unCorrectRatingId_unCorrectRatingLowerBound(){
        Integer userId = 15;
        Integer filmId = 10;
        Integer ratingId = 15;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(15);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(-5);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = -5;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Неполное тестирование + класс плохих данных
    @Test
    public void setRating_unCorrectFilmIdNull_unCorrectUserIdNull_unCorrectRatingId_CorrectRating(){
        Integer userId = null;
        Integer filmId = null;
        Integer ratingId = 15;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(15);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(5);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 5;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }

    //Неполное тестирование + класс плохих данных
    @Test
    public void setRating_unCorrectFilmIdNull_CorrectUserId_unCorrectRatingId_CorrectRating(){
        Integer userId = 3;
        Integer filmId = null;
        Integer ratingId = 15;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(15);
        ratingModel.setFilmId(10);
        ratingModel.setUserId(15);
        ratingModel.setRating(5);
        doReturn(null).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 5;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);
        verify(ratingRepository,never()).save(ratingModel);

        Assertions.assertEquals(-1, actual);
    }


    //Тесты без мока

    @Test
    public void setRating_correctFilmId_correctUserId_correctRatingId_correctRating2_withoutMock(){
        Integer userId = 1;
        Integer filmId = 1;
        Integer ratingId = 3;
        RatingModel ratingModel = new RatingModel();
        ratingModel.setRatingId(3);
        ratingModel.setFilmId(1);
        ratingModel.setUserId(1);
        ratingModel.setRating(2);
        doReturn(ratingModel).when(ratingRepository).findRatingModelByFilmIdAndUserId(filmId, userId);
        Integer rating = 2;

        Integer actual = ratingService.setRating(rating, userId, filmId, ratingId);




        Assertions.assertEquals(2, actual);
    }
}
