package com.agasalha.PetTrackAPI.domain.dtos.user.response;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class UserResponseDto {

    private Long id;
    private String name;
    private  String email;
    private String password;
    private String cpf_cnpj;
    private  String cep;

}
