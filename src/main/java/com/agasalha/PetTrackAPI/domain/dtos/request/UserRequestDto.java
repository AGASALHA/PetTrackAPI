package com.agasalha.PetTrackAPI.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserRequestDto {


    private String name;
    private  String email;
    private String password;
    private String cpf_cnpj;
    private String cep;
}
