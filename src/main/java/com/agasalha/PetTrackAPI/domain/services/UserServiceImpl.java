package com.agasalha.PetTrackAPI.domain.services;


import com.agasalha.PetTrackAPI.domain.dtos.user.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}

    public UserResponseDto save (UserRequestDto userRequestDto){
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        user.setCep(userRequestDto.getCep());

        User savedUser = userRepository.save(user);


        return getUserDto(savedUser);
    }



    private UserResponseDto getUserDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setCpf_cnpj(user.getCpf_cnpj());
        userResponseDto.setCep(user.getCep());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }



}
