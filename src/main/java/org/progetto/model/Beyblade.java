package org.progetto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonValue;
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
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ratchet_id")
    private List<TipoBeybladeRatchet> ratchet = new ArrayList<>();
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "punta_id")
    private List<TipoBeybladePunte> punta = new ArrayList<>();
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "blades_id")
    private List<TipoBeybladeBlades> blades = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "name_category")
    private Category category;
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;


}

