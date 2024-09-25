package com.agasalha.PetTrackAPI.domain.services;


import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.pet.response.PetResponseDTO;
import com.agasalha.PetTrackAPI.domain.dtos.user.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface{

    private final UserRepository userRepository;
    private final PetServiceInterface petService;
    private final PetRepository petRepository;




    @Autowired
    public UserServiceImpl(UserRepository userRepository,PetRepository petRepository,PetServiceInterface petService)
        {
            this.userRepository = userRepository;
            this.petService = petService;
            this.petRepository = petRepository;
        }

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

    @Override
    public UserResponseDto updateById(Long id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            user.setName(userRequestDto.getName());
            user.setCep(userRequestDto.getCep());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(userRequestDto.getPassword());
            user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
            user.setCep(userRequestDto.getCep());

            User updateUser = userRepository.save(user);

            return getUserDto(updateUser);
        } else{
            throw new RuntimeException("User nor found with id: "+ id);
        }
    }


    @Override
    @Transactional
    public UserResponseDto addPetToUser(Long userId, PetRequestDTO novoPet) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PetResponseDTO petResponseDTO = petService.save(novoPet);

        user.addPet(petRepository.findPetById(petResponseDTO.getPet_id()).orElseThrow());

        userRepository.save(user);

        return getUserDto(user);
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


    //atualizar Pet
    //@Override
    

}
