package com.agasalha.PetTrackAPI.domain.dtos.pet.request.request;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.Data;

@Data
@NoArgsConstructor
public class PetRequestDTO {

    private String name;
    private String raca;
    private String peso;
    private String idade;

}
