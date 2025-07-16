package org.progetto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.progetto.model.tipologiche.TipoCardsRarity;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pokemon")
public class Pokemon extends CollectionItem {
    @Column(name = "languageItem")
    private String language;
    @OneToMany
    private List<TipoCardsRarity> rarity;
    @Column(name = "BuyPrice")
    private Double buyPrice;
    @Column(name = "CurrentPrice")
    private Double currentPrice;
    @Column(name = "set_carte")
    private String edition;
    @Column(name = "cardNumber")
    private String cardNumber;
    @ManyToOne
    private Cards cards;


}
