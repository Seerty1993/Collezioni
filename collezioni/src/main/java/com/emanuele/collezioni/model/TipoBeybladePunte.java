package com.emanuele.collezioni.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipo_beyblade_punte")
public class TipoBeybladePunte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, name = "namePoint")
    private String namePoint;
    @Column(unique = true, nullable = false, name = "weight")
    private Double weight;
    @Column(unique = true, nullable = false, name = "originBeyFrom", columnDefinition = "TEXT")
    private String originBeyFrom;
    @Column(unique = true, nullable = false,name = "owned")
    private boolean owned;
    @OneToOne(mappedBy = "punta")
    private Beyblade beyblade;

}
