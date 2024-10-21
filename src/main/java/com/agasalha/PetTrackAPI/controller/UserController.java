package com.agasalha.PetTrackAPI.controller;


import com.agasalha.PetTrackAPI.domain.dtos.pet.request.PetRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.user.request.UserRequestDto;
import com.agasalha.PetTrackAPI.domain.dtos.user.response.UserResponseDto;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.domain.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")

public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public UserResponseDto save (@RequestBody UserRequestDto userRequestDto){

        return userService.save(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateById(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateById(id, userRequestDto);
    }

    @PostMapping("/new_pet/{id}")
    public UserResponseDto addPetToUser(
                @PathVariable Long userId,
                @RequestBody PetRequestDTO petRequestDTO
            ){
        return userService.addPetToUser(userId, petRequestDTO);
        }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {userService.deleteUser(id); }
//todo finalizar CRUD USER

    }

