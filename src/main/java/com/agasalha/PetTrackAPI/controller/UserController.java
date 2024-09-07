package com.agasalha.PetTrackAPI.controller;


import com.agasalha.PetTrackAPI.domain.dtos.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")

public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
   public UserResponseDto save (@RequestBody UserRequestDto userRequestDto){

        return userService.save(userRequestDto);
    }



    }

