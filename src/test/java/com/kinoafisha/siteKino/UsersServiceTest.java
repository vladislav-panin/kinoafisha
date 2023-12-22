package com.kinoafisha.siteKino;

import com.kinoafisha.siteKino.model.FilmModel;
import com.kinoafisha.siteKino.model.RatingModel;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.repository.UsersRepository;
import com.kinoafisha.siteKino.service.UsersService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UsersService usersService;


    //Класс хороших данных
    @Test
    public void registerUser1_usersModelInBdEqualsZero_uniqueLogin_correctPassword(){
        String login = "velolog2";
        String password = "Gagatun_1234";
        UsersModel usersModelInBd = new UsersModel();
        usersModelInBd.setLogin(login);
        usersModelInBd.setPassword(password);
        doReturn(null).when(usersRepository).findByLogin(login);

        String actual = usersService.registerUser1(login, password);
        verify(usersRepository,times(1)).save(usersModelInBd);

        Assertions.assertEquals("success", actual);
    }

    //Класс плохих данных
    @Test
    public void registerUser1_usersModelInBdEqualsZero_nullLogin_nullPassword(){
        String login = null;
        String password = null;
        UsersModel usersModelInBd = new UsersModel();
        usersModelInBd.setLogin(login);
        usersModelInBd.setPassword(password);
        doReturn(null).when(usersRepository).findByLogin(login);

        String actual = usersService.registerUser1(login, password);
        verify(usersRepository,times(1)).save(usersModelInBd);

        Assertions.assertEquals("success", actual);
    }

    //Класс плохих данных
    @Test
    public void registerUser1_usersModelInBdEqualsZero_emptyLogin_emptyPassword(){
        String login = "";
        String password = "";
        UsersModel usersModelInBd = new UsersModel();
        usersModelInBd.setLogin(login);
        usersModelInBd.setPassword(password);
        doReturn(null).when(usersRepository).findByLogin(login);

        String actual = usersService.registerUser1(login, password);
        verify(usersRepository,times(1)).save(usersModelInBd);

        Assertions.assertEquals("success", actual);
    }

    //Класс хороших данных
    @Test
    public void registerUser1_usersModelInBdEqualsZero_notUniqueLogin(){
        String login = "velolog";
        String password = "Gagatun_123";
        UsersModel usersModelInBd = new UsersModel();
        usersModelInBd.setLogin(login);
        usersModelInBd.setPassword(password);
        doReturn(usersModelInBd).when(usersRepository).findByLogin(login);

        String actual = usersService.registerUser1(login, password);
        verify(usersRepository,never()).save(usersModelInBd);

        Assertions.assertEquals("not success", actual);
    }

    //Класс плохих данных
    @Test
    public void registerUser1_usersModelInBdEqualsZero_uniqueLogin_emptyPassword(){
        String login = "velolog2";
        String password = "";
        UsersModel usersModelInBd = new UsersModel();
        usersModelInBd.setLogin(login);
        usersModelInBd.setPassword(password);
        doReturn(null).when(usersRepository).findByLogin(login);

        String actual = usersService.registerUser1(login, password);
        verify(usersRepository,times(1)).save(usersModelInBd);

        Assertions.assertEquals("success", actual);
    }
}
