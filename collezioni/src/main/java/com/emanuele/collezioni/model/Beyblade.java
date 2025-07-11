package com.emanuele.collezioni.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.emanuele.collezioni.model.enumModel.BeybladeFormato;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "beyblade")
public class Beyblade extends CollectionItem {
        @Column(name = "formato")
        private BeybladeFormato formato;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ratchet_id")
        private TipoBeybladeRatchet ratchet;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "punta_id")
        private TipoBeybladePunte punta;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "blades_id")
        private TipoBeybladeBlades blades;
        @Column(name = "aquiredItem")
        private boolean acquired;
        @Column(name = "owned")
        private boolean owned;
        @Column(name = "wish")
        private boolean wish;
        @Column(name = "quantityItem")
        private Integer quantity;
        @ManyToOne
        @JoinColumn(name = "category_id")
        private Category category;
        @Column(name = "note")
        private String note;





    }

