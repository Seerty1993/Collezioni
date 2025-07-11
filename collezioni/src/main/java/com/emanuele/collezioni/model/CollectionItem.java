package com.emanuele.collezioni.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class CollectionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true , name = "nameItem")
    private String name;
    @Column(name = "descriptionItem")
    private String description;
    @Column(name = "quantityItem")
    private Integer quantity;

}


