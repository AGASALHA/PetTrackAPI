package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.user.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.Pet;

public interface UserServiceInterface {
    UserResponseDto save (UserRequestDto userRequestDto);
    UserResponseDto addPetToUser(Long userId, PetRequestDTO novoPet);
}