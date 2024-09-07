package com.agasalha.PetTrackAPI.domain.services;


import com.agasalha.PetTrackAPI.controller.UserController;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserServiceImpl implements UserServiceInterface{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    };

}
