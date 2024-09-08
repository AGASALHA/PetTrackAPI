package com.agasalha.PetTrackAPI.domain.services.qrcode_services;

import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;

public interface QRCodeServiceInterface {
    QRCodeResponseDTO save(QRCodeRequestDTO qrCodeRequestDTO);
}
