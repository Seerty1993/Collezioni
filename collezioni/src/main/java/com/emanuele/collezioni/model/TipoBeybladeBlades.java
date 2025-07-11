package com.emanuele.collezioni.model;

import com.emanuele.collezioni.model.enumModel.BeybladeSpin;
import com.emanuele.collezioni.model.enumModel.BeybladeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_beyblade_blades")
public class TipoBeybladeBlades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, name = "spin")
    private BeybladeSpin spin;
    @Column(unique = true, name = "type")
    private BeybladeType type;
    @Column(unique = true, name = "weight")
    private Double weight;
    @Column(unique = true, nullable = false, name = "nameBlades")
    private String nameBlades;
    @OneToOne(mappedBy = "blades")
    private Beyblade beyblade;
}
