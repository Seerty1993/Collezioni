package com.emanuele.collezioni.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.emanuele.collezioni.model.enumModel.CardRarity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public abstract class Cards extends CollectionItem {
    @Column(name = "languageItem")
    private String language;
    @Column(name = "rarityItem")
    private CardRarity rarity;
    @Column(name = "aquiredItem")
    private boolean acquired;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "cards",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Pokemon> pokemon = new ArrayList<>();





}
