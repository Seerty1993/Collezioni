package org.progetto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.progetto.model.enumModel.BeybladeFormato;
import org.progetto.model.tipologiche.TipoBeybladeBlades;
import org.progetto.model.tipologiche.TipoBeybladePunte;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beyblade")
public class Beyblade extends CollectionItem {
    @Column(name = "formato")
    private BeybladeFormato formato;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ratchet_id")
    private TipoBeybladeRatchet ratchet;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "punta_id")
    private TipoBeybladePunte punta;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blades_id")
    private TipoBeybladeBlades blades;
    @ManyToOne
    @JoinColumn(name = "name_category")
    private Category category;
    @Column(name = "note")
    private String note;


}

