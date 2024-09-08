package com.agasalha.PetTrackAPI.domain.services.qrcode_services;

import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import com.agasalha.PetTrackAPI.domain.entities.QRCode;
import com.agasalha.PetTrackAPI.infrastructure.repository.QRCodeRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class QRCodeServiceImpl implements QRCodeServiceInterface{

    private final QRCodeRepository qrCodeRepository;

    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository){this.qrCodeRepository = qrCodeRepository;};

    @Override
    public QRCodeResponseDTO save(QRCodeRequestDTO qrCodeRequestDTO) {
        QRCode qrCode = new QRCode();
        qrCode.setUuid(generateUUID());
        qrCode.setPet((petRepository.findById(qrCodeRequestDTO.getPet_id))).orElseThrow(); //preciso do repositorio CRUD de Pets!
        qrCode.setActivation_date(LocalDate.now());
        qrCode.setIs_active(Boolean.TRUE);
        QRCode savedQRCode = qrCodeRepository.save(qrCode);

        QRCodeResponseDTO qrCodeResponseDTO = new QRCodeResponseDTO();
        qrCodeResponseDTO.setUuid(savedQRCode.getUuid());
        qrCodeResponseDTO.setPet_id(savedQRCode.getPet().getId());
        qrCodeResponseDTO.setQrcode_id(savedQRCode.getId());
        qrCodeResponseDTO.setIs_active(savedQRCode.getIs_active());
        qrCodeResponseDTO.setActivation_date(savedQRCode.getActivation_date());

        return qrCodeResponseDTO;

    }

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String uuid_string = uuid.toString();
        Optional<QRCode> existingQR = qrCodeRepository.findByUUID(uuid_string);
        if(existingQR.isPresent()){
            return generateUUID();
        } else {
            return uuid_string;
        }
    }
}
