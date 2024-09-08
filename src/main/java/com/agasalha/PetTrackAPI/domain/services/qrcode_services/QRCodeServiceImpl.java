package com.agasalha.PetTrackAPI.domain.services.qrcode_services;

import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.entities.QRCode;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class QRCodeServiceImpl implements QRCodeServiceInterface{

    private final QRCodeRepository qrCodeRepository;
    private final PetRepository petRepository;

    //Construtor
    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository, PetRepository petRepository){
        this.qrCodeRepository = qrCodeRepository;
        this.petRepository = petRepository;
    };

    @Override
    public QRCodeResponseDTO save(QRCodeRequestDTO qrCodeRequestDTO) {
        //cria novo QrCode
        QRCode qrCode = new QRCode();
        qrCode.setUuid(generateUUID());
        // Obter o ID do pet Dto
        Long petId = qrCodeRequestDTO.getPet_id();
        //Busca pet pelo ID
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        qrCode.setPet(pet);
        //Configura outros campos do Qrcode
        qrCode.setActivation_date(LocalDate.now());
        qrCode.setIs_active(Boolean.TRUE);
        //Salva o Qrcode no repository
        QRCode savedQRCode = qrCodeRepository.save(qrCode);

        //Cria o DTO da resposta
        QRCodeResponseDTO qrCodeResponseDTO = new QRCodeResponseDTO();
        qrCodeResponseDTO.setUuid(savedQRCode.getUuid());
        qrCodeResponseDTO.setPet_id(savedQRCode.getPet().getId());
        qrCodeResponseDTO.setQrcode_id(savedQRCode.getId());
        qrCodeResponseDTO.setIs_active(savedQRCode.getIs_active());
        qrCodeResponseDTO.setActivation_date(savedQRCode.getActivation_date());

        return qrCodeResponseDTO;

    }
    //gera um Id unico
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
