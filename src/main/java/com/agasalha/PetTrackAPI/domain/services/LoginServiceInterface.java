package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.login.LoginRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.login.LoginResponseDto;

public interface LoginServiceInterface {
    LoginResponseDto login(LoginRequestDto loginRequest);
}
