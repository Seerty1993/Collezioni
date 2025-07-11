package org.progetto.model;

import io.ebean.Model;
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
@Table(name = "category", schema = "collezioni")
public class Category extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "name_category")
    private String name_category;
    @OneToMany
    private List<Cards> cards = new ArrayList<>();
    @OneToMany
    private List<Beyblade> beyblades = new ArrayList<>();

}
