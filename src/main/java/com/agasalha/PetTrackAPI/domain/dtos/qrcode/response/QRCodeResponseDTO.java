package com.agasalha.PetTrackAPI.domain.dtos.qrcode.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QRCodeResponseDTO {
    public long qrcode_id;
    public long pet_id;
    private long user_id;
    public String uuid;
    public LocalDate activation_date;
    public Boolean is_active;
}
