package com.agasalha.PetTrackAPI.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class UserResponseDto {

    private String name;
    private  String email;
    private String password;
    private String cpf_cnpj;
}
