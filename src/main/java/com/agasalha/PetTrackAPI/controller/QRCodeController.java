package com.agasalha.PetTrackAPI.controller;


import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import com.agasalha.PetTrackAPI.domain.services.qrcode_services.QRCodeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/qrcode/")
public class QRCodeController {
    private final QRCodeServiceInterface qrCodeService;

    @Autowired
    public QRCodeController (QRCodeServiceInterface qrCodeService){this.qrCodeService = qrCodeService;}

    @PostMapping("/save")
    public QRCodeResponseDTO save (QRCodeRequestDTO qrCodeRequestDTO){
        return  qrCodeService.save(qrCodeRequestDTO);
    }

}
