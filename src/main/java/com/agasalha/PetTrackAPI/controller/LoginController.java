package com.agasalha.PetTrackAPI.controller;

import com.agasalha.PetTrackAPI.domain.dtos.login.LoginRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.login.LoginResponseDto;
import com.agasalha.PetTrackAPI.domain.services.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {


    private final LoginServiceInterface loginService;

    public LoginController(LoginServiceInterface loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return loginService.login(loginRequestDto);
    }
}
