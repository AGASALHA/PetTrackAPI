package com.agasalha.PetTrackAPI.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pet")
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
    private String raca;

    @Column(nullable = false)
    private String peso;

    @Column
    private String idade;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
