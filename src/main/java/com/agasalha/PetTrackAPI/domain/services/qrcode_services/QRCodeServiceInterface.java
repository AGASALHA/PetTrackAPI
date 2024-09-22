package com.agasalha.PetTrackAPI.domain.services.qrcode_services;

import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import io.nayuki.qrcodegen.QrCode;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface QRCodeServiceInterface {
    QRCodeResponseDTO save(QRCodeRequestDTO qrCodeRequestDTO) throws IOException;
    QRCodeResponseDTO get(Long id);
    String generateUUID();
    byte[] generateBlobFromImg(String uuid) throws IOException;
    BufferedImage generateImgFromQrCode(QrCode qr, int scale, int border, int lightColor, int darkColor);
}
