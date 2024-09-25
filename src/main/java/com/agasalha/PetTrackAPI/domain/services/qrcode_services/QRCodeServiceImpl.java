package com.agasalha.PetTrackAPI.domain.services.qrcode_services;

import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import com.agasalha.PetTrackAPI.domain.entities.Pet;
import com.agasalha.PetTrackAPI.domain.entities.QRCode;
import com.agasalha.PetTrackAPI.domain.entities.User;
import com.agasalha.PetTrackAPI.infrastructure.repository.PetRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.QRCodeRepository;
import com.agasalha.PetTrackAPI.infrastructure.repository.UserRepository;
import io.nayuki.qrcodegen.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;
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
    public QRCodeResponseDTO save(QRCodeRequestDTO qrCodeRequestDTO) throws IOException {

        QRCode qrCode = new QRCode();

        String uuid = generateUUID();

        qrCode.setUUID(uuid);
        qrCode.setBlob(generateBlobFromImg(uuid));

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
        qrCodeResponseDTO.setUser_id(savedQRCode.getUser().getId());
        qrCodeResponseDTO.setQrcode_id(savedQRCode.getId());
        qrCodeResponseDTO.setBlob(Base64.getEncoder().encodeToString(savedQRCode.getBlob()));
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

    @Override
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


    public byte[] imgToBlob(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }

    @Override
    public byte[] generateBlobFromImg(String uuid) throws IOException {
        QrCode qr = QrCode.encodeText("http://localhost:8081/api/pets/uuid/"+uuid, QrCode.Ecc.MEDIUM);
        BufferedImage img = generateImgFromQrCode(qr, 12,4, 0xFFFFFF, 0x000000);
        return imgToBlob(img);

    }

    /* FROM nayuki/QR-Code-generator to_image method on GITHUB */
    @Override
    public BufferedImage generateImgFromQrCode(QrCode qr, int scale, int border, int lightColor, int darkColor) {
        Objects.requireNonNull(qr);
        if (scale <= 0 || border < 0)
            throw new IllegalArgumentException("Value out of range");
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
            throw new IllegalArgumentException("Scale or border too large");

        BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }

        return result;
    }

    @Override
    public QRCodeResponseDTO getByUUID(String uuid) {
        QRCode qrCodeByUUID = qrCodeRepository.findByUUID(uuid)
                .orElseThrow(() -> new RuntimeException("QR não encontrado com ID " + uuid));
        return this.buildQRCodeResponseDTO(qrCodeByUUID);
    }

    public QRCodeResponseDTO buildQRCodeResponseDTO(QRCode qrcode){
        QRCodeResponseDTO responseDTO = new QRCodeResponseDTO();

        responseDTO.setActivation_date(qrcode.getActivation_date());
        responseDTO.setUuid(qrcode.getUUID());
        responseDTO.setIs_active(qrcode.getIs_active());
        responseDTO.setPet_id(qrcode.getPet().getId());
        responseDTO.setUser_id(qrcode.getUser().getId());
        responseDTO.setQrcode_id(qrcode.getId());

        return responseDTO;
    }

    //TODO GET QR CODE IMG



}
