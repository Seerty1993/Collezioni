package org.progetto.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public abstract class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "name_category")
    private Category category;
    @JsonManagedReference
    @OneToMany(mappedBy = "cards",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Pokemon> pokemon = new ArrayList<>();


}
