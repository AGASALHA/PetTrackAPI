package com.agasalha.PetTrackAPI.domain.services.qrcode_services;

import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.entities.QRCode;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.QRCodeRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class QRCodeServiceImpl implements QRCodeServiceInterface{

    private final QRCodeRepository qrCodeRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Autowired
    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository, UserRepository userRepository, PetRepository petRepository){
        this.qrCodeRepository = qrCodeRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;

    };

    @Override
    public QRCodeResponseDTO save(QRCodeRequestDTO qrCodeRequestDTO) {
        //cria novo QrCode
        QRCode qrCode = new QRCode();
        qrCode.setUUID(generateUUID());
        Pet pet = petRepository.findById(qrCodeRequestDTO.getPet_id()).orElseThrow(() -> new RuntimeException("Pet not found"));
        qrCode.setPet(pet);
        User user = userRepository.findById(qrCodeRequestDTO.getUser_id()).orElseThrow(() -> new RuntimeException("User not found"));
        qrCode.setUser(user);

        qrCode.setActivation_date(LocalDate.now());
        qrCode.setIs_active(Boolean.TRUE);
        //Salva o Qrcode no repository
        QRCode savedQRCode = qrCodeRepository.save(qrCode);

        //Cria o DTO da resposta
        QRCodeResponseDTO qrCodeResponseDTO = new QRCodeResponseDTO();
        qrCodeResponseDTO.setUuid(savedQRCode.getUUID());
        qrCodeResponseDTO.setPet_id(savedQRCode.getPet().getId());
        qrCodeResponseDTO.setQrcode_id(savedQRCode.getId());
        qrCodeResponseDTO.setIs_active(savedQRCode.getIs_active());
        qrCodeResponseDTO.setActivation_date(savedQRCode.getActivation_date());

        return qrCodeResponseDTO;

    }

    @Override
    public QRCodeResponseDTO get(Long id) {
        QRCode foundQRcode = qrCodeRepository.findById(id).orElseThrow();
        QRCodeResponseDTO responseDTO = new QRCodeResponseDTO();
        responseDTO.setActivation_date(foundQRcode.getActivation_date());
        responseDTO.setUuid(foundQRcode.getUUID());
        responseDTO.setIs_active(foundQRcode.getIs_active());
        responseDTO.setPet_id(foundQRcode.getPet().getId());
        responseDTO.setUser_id(foundQRcode.getUser().getId());
        responseDTO.setQrcode_id(foundQRcode.getId());

        return responseDTO;
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
