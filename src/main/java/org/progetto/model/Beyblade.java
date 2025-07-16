package org.progetto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.progetto.model.enumModel.BeybladeFormato;
import org.progetto.model.tipologiche.TipoBeybladeBlades;
import org.progetto.model.tipologiche.TipoBeybladePunte;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beyblade")
public class Beyblade extends CollectionItem {
    @Column(name = "formato")
    @Enumerated(EnumType.STRING)
    private BeybladeFormato formato;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private TipoBeybladeRatchet ratchet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private TipoBeybladePunte punta;
    @OneToOne
    private TipoBeybladeBlades blade;
    @ManyToOne
    @JsonBackReference
    private Category category;
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;


}

