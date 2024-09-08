package com.agasalha.PetTrackAPI.infrastructure.repository;

import com.agasalha.PetTrackAPI.domain.entities.QRCode;
import com.agasalha.PetTrackAPI.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    Optional<QRCode> findByUUID(String uuid);
}
