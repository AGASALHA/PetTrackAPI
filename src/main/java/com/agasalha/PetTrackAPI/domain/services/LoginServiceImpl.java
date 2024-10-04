package com.agasalha.PetTrackAPI.domain.services;

import com.agasalha.PetTrackAPI.domain.dtos.login.LoginRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.login.LoginResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {

        Optional<User> userteste = userRepository.findByEmail(loginRequest.getEmail());
        LoginResponseDto response = new LoginResponseDto();

        if (userteste.isEmpty()) {
            response.setMessage("Login recusado: Usuário não encontrado.");
            return response;
        }

        User user = userteste.get();

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            response.setMessage("Login recusado: Senha incorreta.");
            return response;
        }

        response.setMessage("Login feito com sucesso!");
        return response;
    }}
