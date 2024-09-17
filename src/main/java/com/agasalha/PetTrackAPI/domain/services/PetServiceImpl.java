package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.pet.response.PetResponseDTO;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetServiceInterface {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    //salva novo pet
    @Override
    public PetResponseDTO save(PetRequestDTO petRequestDTO) {

        Pet pet = new Pet();

        pet.setName(petRequestDTO.getName());
        pet.setRaca(petRequestDTO.getRaca());
        pet.setPeso(petRequestDTO.getPeso());
        pet.setIdade(petRequestDTO.getIdade());

        User user = userRepository.findById(petRequestDTO.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        pet.setUser(user);

        Pet savedPet = petRepository.save(pet);

        return get(savedPet);
    }


    //Converter para petResponseDTO
    private PetResponseDTO get(Pet pet) {
        PetResponseDTO responseDTO = new PetResponseDTO();
        responseDTO.setName(pet.getName());
        responseDTO.setRaca(pet.getRaca());
        responseDTO.setPeso(pet.getPeso());
        responseDTO.setIdade(pet.getIdade());
        responseDTO.setPet_id(pet.getId());
        return responseDTO;
    }


    //atualizar pet
    @Override
    public PetResponseDTO updatePet(Long Id, PetRequestDTO petRequestDTO) {
        Optional<Pet> optionalPet = petRepository.findPetById(Id);

        if (optionalPet.isPresent()) {

            Pet pet = optionalPet.get();
            pet.setName(petRequestDTO.getName());
            pet.setRaca(petRequestDTO.getRaca());
            pet.setPeso(petRequestDTO.getPeso());
            pet.setIdade(petRequestDTO.getIdade());

            Pet updatePet = petRepository.save(pet);
            return get(updatePet);
        } else {
            throw new RuntimeException("Pet not found with id: " + Id);
        }
    }

    //deletar pet
    @Override
    public void deletePet(Long Id) {
        Optional<Pet> optionalPet = petRepository.findById(Id);
        if(optionalPet.isPresent()){
            petRepository.deleteById(Id);
        } else {
            throw new RuntimeException("Pet not found with id: " + Id);
        }
    }

    @Override
    public List<PetResponseDTO> findAllPets() {
        return List.of();
    }

    @Override
    public Optional<PetResponseDTO> findPetById(Long Id) {
        return Optional.empty();
    }
}