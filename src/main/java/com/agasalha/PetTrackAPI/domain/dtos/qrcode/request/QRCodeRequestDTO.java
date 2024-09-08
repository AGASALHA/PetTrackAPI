package com.agasalha.PetTrackAPI.domain.dtos.qrcode.request;

import com.agasalha.PetTrackAPI.domain.entities.Pet;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class QRCodeRequestDTO {
    private long pet_id;
    private long user_id;
    private String uuid;
    private LocalDate activation_date;
    private Boolean is_active;
}
