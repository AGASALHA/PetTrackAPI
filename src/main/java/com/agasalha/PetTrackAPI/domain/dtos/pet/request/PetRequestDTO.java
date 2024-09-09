package com.agasalha.PetTrackAPI.domain.dtos.pet.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetRequestDTO {

    public String name;
    public   String raca;
    public String peso;
    public String idade;
}
