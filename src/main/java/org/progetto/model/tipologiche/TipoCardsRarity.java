package org.progetto.model.tipologiche;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.progetto.model.Pokemon;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tipo_cards_rarity")
public class TipoCardsRarity {
    @Id
    @Column(nullable = false, name = "cards_rarity")
    private String cards_rarity;
    @ManyToOne
    private Pokemon pokemon;
}
