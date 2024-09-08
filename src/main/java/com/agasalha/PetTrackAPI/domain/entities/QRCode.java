package com.agasalha.PetTrackAPI.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Pet")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column
    private String uuid;

    @Column
    private LocalDate activation_date;

    @Column
    private Boolean is_active;

}
