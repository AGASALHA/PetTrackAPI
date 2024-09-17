package com.agasalha.PetTrackAPI.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Pet")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String UUID;

    @Column
    private LocalDate activation_date;

    @Column
    private Boolean is_active;

}
