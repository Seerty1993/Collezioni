package org.progetto.model.tipologiche;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.progetto.model.Beyblade;
import org.progetto.model.enumModel.BeybladeSpin;
import org.progetto.model.enumModel.BeybladeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipo_beyblade_blades")
public class TipoBeybladeBlades extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "spin")
    @Enumerated(EnumType.STRING)
    private BeybladeSpin spin;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BeybladeType type;
    @Column(name = "weight")
    private Double weight;
    @Column(nullable = false, name = "nameBlades")
    private String nameBlades;
    @Column(nullable = false, name = "owned")
    private boolean owned;
    @JsonBackReference
    @ManyToMany
    private List<Beyblade> beyblade = new ArrayList<>();
}
