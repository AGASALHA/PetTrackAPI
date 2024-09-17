package com.agasalha.PetTrackAPI.domain.dtos.pet.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor

public class PetResponseDTO {

    //TODO ADICIONAR PET_ID NO RETORNO DAS REQUISIÇÕES
    private String name;
    private String raca;
    private String peso;
    private String idade;

}
