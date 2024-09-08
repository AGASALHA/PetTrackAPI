package com.agasalha.PetTrackAPI.infrastructure.repository;

import com.agasalha.PetTrackAPI.domain.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByName(String name);

}
