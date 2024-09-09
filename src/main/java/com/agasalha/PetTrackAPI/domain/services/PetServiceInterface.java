package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.pet.response.PetResponseDTO;

import java.util.Optional;

public interface PetServiceInterface {

    PetResponseDTO save (PetResponseDTO petResponseDTO);

    List<PetResponseDTO> findAllPets();

    Optional<PetResponseDTO> findPetById(Long Id);

}
