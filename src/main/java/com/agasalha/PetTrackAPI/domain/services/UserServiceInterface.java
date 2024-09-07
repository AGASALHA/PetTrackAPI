package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.response.UserResponseDto;

public interface UserServiceInterface {


    UserResponseDto save (UserRequestDto userRequestDto);

}
