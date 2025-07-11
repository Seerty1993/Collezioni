package com.emanuele.collezioni.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipo_beyblade_ratchet")
public class TipoBeybladeRatchet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, name = "Bumps")
    private Integer nBumps;
    @Column(unique = true, nullable = false, name = "Height")
    private Integer height;
    @Column(unique = true, nullable = false, name = "Weight")
    private Double weight;
    @Column(unique = true, nullable = false, name = "originBeyFrom", columnDefinition = "TEXT")
    private String originBeyFrom;
    @OneToOne(mappedBy = "ratchet")
    private Beyblade beyblade;



}
