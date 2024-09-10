package com.agasalha.PetTrackAPI.controller;


import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.pet.response.PetResponseDTO;
import com.agasalha.PetTrackAPI.domain.dtos.user.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.services.PetServiceInterface;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetServiceInterface petService;

    @Autowired
    public PetController(PetServiceInterface petService) {
        this.petService = petService;
    }

    @PostMapping("/cadastrar")
    public PetResponseDTO save(@RequestBody PetRequestDTO petRequestDTO) {
        return petService.save(petRequestDTO);
    }

    @GetMapping
    public List<PetResponseDTO> getAllPets() {
        return petService.findAllPets();
    }

    @GetMapping("/{id}")
    public PetResponseDTO getPetById(@PathVariable Long id) {
        return petService.findPetById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }

    @PutMapping("/{id}")
        public PetResponseDTO updatePet(@PathVariable Long id, @RequestBody PetRequestDTO petRequestDTO) {
            return petService.updatePet(id, petRequestDTO);
        }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
