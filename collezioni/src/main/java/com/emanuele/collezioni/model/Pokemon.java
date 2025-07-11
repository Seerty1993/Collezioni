package com.emanuele.collezioni.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "BuyPrice")
    private Double buyPrice;
    @Column(name = "CurrentPrice")
    private Double currentPrice;
    @Column(name = "Set")
    private String edition;
    @Column(name = "cardNumber")
    private String cardNumber;
    @ManyToOne
    @JoinColumn(name = "cards_id")
    private Cards cards;



}
