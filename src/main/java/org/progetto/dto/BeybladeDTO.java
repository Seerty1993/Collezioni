package org.progetto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.progetto.model.Beyblade;
import org.progetto.model.Category;
import org.progetto.model.enumModel.BeybladeFormato;
import org.progetto.model.tipologiche.TipoBeybladeBlades;
import org.progetto.model.tipologiche.TipoBeybladePunte;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeybladeDTO {
    private Integer id;
    private String name;
    private String description;
    private boolean owned;
    private boolean wish;
    private Integer quantity;
    private BeybladeFormato formato;
    private Integer ratchet;
    private Integer punta;
    private Integer blades;
    private Integer category;
    private String note;

//    public static BeybladeDTO of(Beyblade entity) {
//        BeybladeDTO dto = new BeybladeDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setDescription(entity.getDescription());
//        dto.setOwned(entity.isOwned());
//        dto.setWish(entity.isWish());
//        //  QUA
//        dto.setQuantity(entity.getQuantity());
//        dto.setFormato(entity.getFormato());
//        dto.setRatchet(entity.getRatchet());
//        dto.setPunta(entity.getPunta());
//        dto.setBlades(entity.getBlades());
//        dto.setCategory(entity.getCategory());
//        dto.setNote(entity.getNote());
//        return dto;
//    }

    public Beyblade toEntity() {
        Beyblade entity = new Beyblade();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setOwned(this.owned);
        entity.setWish(this.wish);
        entity.setQuantity(this.quantity);
        entity.setFormato(this.formato);
        entity.setNote(this.note);
        toUpdate(entity);
        return entity;
    }

    public void toUpdate(Beyblade entity) {
        if (this.name != null) {
            entity.setName(this.name);
        }
        if (this.description != null) {
            entity.setDescription(this.description);
        }
        if (!this.owned) {
            entity.setOwned(this.owned);
        }
        if (!this.wish) {
            entity.setWish(this.wish);
        }
        if (this.quantity != null) {
            entity.setQuantity(this.quantity);
        }
        if (this.formato != null) {
            entity.setFormato(this.formato);
        }
        if (this.ratchet != null) {
            entity.setRatchet(List.of(entity.db().reference(TipoBeybladeRatchet.class, this.ratchet)));
        }
        if (this.punta != null) {
            entity.setPunta(List.of(entity.db().reference(TipoBeybladePunte.class, this.punta)));
        }
        if (this.blades != null) {
            entity.setBlades(List.of(entity.db().reference(TipoBeybladeBlades.class, this.blades)));
        }
        if (this.category != null) {
            entity.setCategory(entity.db().reference(Category.class, this.category));
        }
        if (this.note != null) {
            entity.setNote(this.note);
        }

    }
}
