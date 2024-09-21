package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.pet.response.PetResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PetServiceInterface {

    //PetResponseDTO save(PetResponseDTO petResponseDTO);

    PetResponseDTO save(PetRequestDTO petRequestDTO);

    PetResponseDTO updatePet(Long id, PetRequestDTO petRequestDTO);

    void deletePet(Long id);

    List<PetResponseDTO> findAllPets();

    Optional<PetResponseDTO> findPetById(Long Id);

}
