package com.agasalha.PetTrackAPI.domain.dtos.pet.request;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.Data;

@Data
@NoArgsConstructor
public class PetRequestDTO {

    private long user_id;
    private String name;
    private String raca;
    private String peso;
    private String idade;
}

