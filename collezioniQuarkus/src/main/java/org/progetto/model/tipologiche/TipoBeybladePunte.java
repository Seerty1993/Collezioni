package org.progetto.model.tipologiche;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.progetto.model.Beyblade;

@Entity
@Getter
@Setter
@Table(name = "tipo_beyblade_punte")
public class TipoBeybladePunte extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "namePoint")
    private String namePoint;
    @Column(nullable = false, name = "weight")
    private Double weight;
    @Column(name = "originBeyFrom", columnDefinition = "TEXT")
    private String originBeyFrom;
    @Column(nullable = false, name = "owned")
    private boolean owned;
    @JsonBackReference
    @OneToOne(mappedBy = "punta")
    private Beyblade beyblade;

}
