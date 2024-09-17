package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.user.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;

public interface UserServiceInterface {


    UserResponseDto save (UserRequestDto userRequestDto);

}