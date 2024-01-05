package com.kinoafisha.siteKino.service;

import com.kinoafisha.siteKino.mapper.UsersMapper;
import com.kinoafisha.siteKino.model.UsersModel;
import com.kinoafisha.siteKino.model.dto.UserProfileDto;
import com.kinoafisha.siteKino.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {


    private final UsersRepository usersRepository;


    private final UsersMapper usersMapper;

    public UsersModel registerUser(String login, String password)
    {
        UsersModel usersModelInBd = usersRepository.findByLogin(login);
        if(usersModelInBd != null)
        {
            String loginInBd = usersModelInBd.getLogin();
            System.out.println(loginInBd);
            return null;
        } else
        {
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            return usersRepository.save(usersModel);
        }
    }

    public String registerUser1(String login, String password)
    {
        UsersModel usersModelInBd = usersRepository.findByLogin(login);
        if(usersModelInBd != null)
        {
            String loginInBd = usersModelInBd.getLogin();
            System.out.println(loginInBd);
            return "not success";
        } else
        {
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersRepository.save(usersModel);
            return "success";
        }
    }

    public void profileUpdate(UsersModel usersModel){

        System.out.println("update_profile request: " + usersModel);
        UsersModel userModelFromDb = usersRepository.findUsersModelByAuthentificated(1);
        String userFromDbLogin = userModelFromDb.getLogin();
        String userFromDbPreferences = userModelFromDb.getPreferences();
        String userFromDbBirthDate = userModelFromDb.getBirthDate();

        if(usersModel.getLogin()=="")
            usersModel.setLogin(userFromDbLogin);
        if(usersModel.getPreferences()=="")
            usersModel.setPreferences(userFromDbPreferences);
        if(usersModel.getBirthDate()=="")
            usersModel.setBirthDate(userFromDbBirthDate);

        userModelFromDb.setLogin(usersModel.getLogin());
        userModelFromDb.setPreferences(usersModel.getPreferences());
        userModelFromDb.setBirthDate(usersModel.getBirthDate());
        usersRepository.save(userModelFromDb);
    }

    public UserProfileDto getProfile(UsersModel usersModel){
        return usersMapper.toUserProfileDto(usersModel);
    }

    public UsersModel authenticate(String login, String password){

        UsersModel user = usersRepository.findByLoginAndPassword(login,password);
        if(user != null)
        {
             return user;
        }
        else {
            return null;
        }
    }

    public UsersModel findAuthentificatedUser() {
        return usersRepository.findUsersModelByAuthentificated(1);
    }


}
