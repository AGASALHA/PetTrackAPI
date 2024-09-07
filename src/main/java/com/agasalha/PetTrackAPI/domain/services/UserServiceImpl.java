package com.agasalha.PetTrackAPI.domain.services;


import com.agasalha.PetTrackAPI.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserServiceImpl implements UserServiceInterface{

    private final UserServiceInterface UserService;

    @Autowired
    public UserController(UserServiceInterface userService){ this userService = userService};

    @PostMapping




}
