package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.pet.response.PetResponseDTO;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetServiceInterface {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetResponseDTO save(PetRequestDTO petRequestDTO) {

        Pet pet = new Pet();

        pet.setName(petRequestDTO.getName());
        pet.setRaca(petRequestDTO.getRaca());
        pet.setPeso(petRequestDTO.getPeso());
        pet.setIdade(petRequestDTO.getIdade());

        Pet savedPet = petRepository.save(pet);

        return get(savedPet);
    }

    private PetResponseDTO getPetDto(Pet pet) {
        UserResponseDto userResponseDto = new UserResponseDto();

}
