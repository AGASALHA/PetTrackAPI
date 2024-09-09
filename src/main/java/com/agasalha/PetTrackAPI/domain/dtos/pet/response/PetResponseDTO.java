package com.agasalha.PetTrackAPI.domain.dtos.pet.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetResponseDTO {

    public Long id;
    public String name;
    public   String raca;
    public String peso;
    public String idade;
}
