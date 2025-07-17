package org.progetto.model.tipologiche;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.progetto.model.Beyblade;
import org.progetto.model.enumModel.BeybladeFormato;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipo_beyblade_ratchet")
public class TipoBeybladeRatchet extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "Bumps")
    private Integer bumps;
    @Column(nullable = false, name = "Height")
    private Integer height;
    @Column(nullable = false, name = "Weight")
    private Double weight;
    @Column(name = "originBeyFrom", columnDefinition = "TEXT")
    private String originBeyFrom;
    @Column(name = "formato")
    @Enumerated(EnumType.STRING)
    private BeybladeFormato formato;
    @Column(nullable = false, name = "owned")
    private Boolean owned;
    @Column(nullable = false, name = "name_ratchet")
    private String nameRatchet;
    @OneToMany
    @JsonManagedReference
    private List<Beyblade> beyblade = new ArrayList<>();

}
