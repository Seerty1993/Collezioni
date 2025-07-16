package org.progetto.model;

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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoBeybladeRatchet> ratchet = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoBeybladePunte> punta = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TipoBeybladeBlades> blades = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "name_category")
    private Category category;
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;


}

