package com.agasalha.PetTrackAPI.controller;


import com.agasalha.PetTrackAPI.domain.dtos.qrcode.request.QRCodeRequestDTO;
import com.agasalha.PetTrackAPI.domain.dtos.qrcode.response.QRCodeResponseDTO;
import com.agasalha.PetTrackAPI.domain.services.qrcode_services.QRCodeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/qrcode/")
public class QRCodeController {
    private final QRCodeServiceInterface qrCodeService;

    @Autowired
    public QRCodeController (QRCodeServiceInterface qrCodeService){this.qrCodeService = qrCodeService;}

    @PostMapping("/save")
    public QRCodeResponseDTO save (@RequestBody QRCodeRequestDTO qrCodeRequestDTO){
        return  qrCodeService.save(qrCodeRequestDTO);
    }

    @GetMapping("/{id}")
    public QRCodeResponseDTO get(@PathVariable long id){
        return qrCodeService.get(id);
    }

}
