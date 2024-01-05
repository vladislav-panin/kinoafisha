package com.kinoafisha.siteKino.controller;

import com.kinoafisha.siteKino.model.*;
import com.kinoafisha.siteKino.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class UsersController {


    private final UsersService usersService;

    private final FormingModelsForUsersService formingModelsForUsersService;


    @GetMapping("/easy1")
    public String simpleReq(){
        return "easy";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @GetMapping("/logout")
    public String getLogoutPage(Model model){
        model.addAttribute("logoutRequest", new UsersModel());
        return "already_logged_in_error";
    }


    @GetMapping("/profile/{userName}")
    public String getOtherUserProfilePage(Model model,@PathVariable String userName){

        UsersModel userModel = usersService.getUserModel(userName);
        formingModelsForUsersService.formingUserProfileModel(model, userModel);
        return "profile_other_user";
    }


    @GetMapping("/profile")
    public String getProfilePage(Model model){

        UsersModel usersModel = usersService.findAuthentificatedUser();
        formingModelsForUsersService.formingUserProfileModel(model, usersModel);
        return "profile_page";
    }

    @GetMapping("/updateProfileGet")
    public String getUpdatePage(Model model){
        model.addAttribute("updateRequest", new UsersModel());
        return "update_profile";
    }


    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UsersModel usersModel)
    {
        usersService.profileUpdate(usersModel);
        return "profile_update_success";
    }


    @GetMapping("tryReg")
    public String tryRegister(){
        usersService.registerVariant2("velolog1", "Gagatun_1234");
        return "success";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel)
    {
        UsersModel registeredUser =  usersService.registerVariant1(usersModel.getLogin(), usersModel.getPassword());

        if(registeredUser != null){
            return "login_page";
        }else{
            return "reg_error_page";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model)
    {
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            formingModelsForUsersService.formingLoginModel(model, usersModel);
            return "main_page";
        }else {
            return "auth_error_page"; //
            }
    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute UsersModel usersModel, Model model)
    {
        usersService.logout();
        formingModelsForUsersService.formingLogoutModel(usersModel, model);

        return "login_page";
    }


}


