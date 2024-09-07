package com.agasalha.PetTrackAPI.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pet")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String raça;

    @Column(nullable = false)
    private String peso;

    @Column(nullable = false)
    private String idade;
}
