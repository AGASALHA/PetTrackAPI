package com.agasalha.PetTrackAPI.controller;


import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.services.PetServiceInterface;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetServiceInterface petService;

    @Autowired
    public PetController (PetServiceInterface petService){
        this.petService = petService;
    }

}

