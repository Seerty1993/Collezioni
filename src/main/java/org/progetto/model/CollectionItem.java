package org.progetto.model;


import io.ebean.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class CollectionItem extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, name = "nameItem")
    private String name;
    @Column(name = "descriptionItem", columnDefinition = "TEXT")
    private String description;
    @Column(name = "owned")
    private boolean owned;
    @Column(name = "wish")
    private boolean wish;
    @Column(name = "quantityItem")
    private Integer quantity = 0;


}


