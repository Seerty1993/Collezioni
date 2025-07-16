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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeybladeDetailDTO {
    private Integer id;
    private String name;
    private String description;
    private boolean owned;
    private boolean wish;
    private Integer quantity;
    private BeybladeFormato formato;
    private String ratchet;
    private String punta;
    private String blades;
    private String category;
    private String note;

    public static BeybladeDetailDTO of(Beyblade entity) {
        BeybladeDetailDTO dto = new BeybladeDetailDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setOwned(entity.isOwned());
        dto.setWish(entity.isWish());
        //  QUA
        dto.setQuantity(entity.getQuantity());
        dto.setFormato(entity.getFormato());
        dto.setRatchet(entity.getRatchet().stream().findFirst().get().getName_ratchet());
        dto.setPunta(entity.getPunta().stream().findFirst().get().getNamePoint());
        dto.setBlades(entity.getBlades().stream().findFirst().get().getNameBlades());
        dto.setCategory(entity.getCategory().getName_category());
        dto.setNote(entity.getNote());
        return dto;
    }

//    public Beyblade toEntity() {
//        Beyblade entity = new Beyblade();
//        entity.setId(this.id);
//        entity.setName(this.name);
//        entity.setDescription(this.description);
//        entity.setOwned(this.owned);
//        entity.setWish(this.wish);
//        entity.setQuantity(this.quantity);
//        entity.setFormato(this.formato);
//        entity.setRatchet(this.ratchet);
//        entity.setPunta(this.punta);
//        entity.setBlades(this.blades);
//        entity.setCategory(this.category);
//        entity.setNote(this.note);
//        toUpdate(entity);
//        return entity;
//    }
//
//    public void toUpdate(Beyblade entity) {
//        if (this.name != null) {
//            entity.setName(this.name);
//        }
//        if (this.description != null) {
//            entity.setDescription(this.description);
//        }
//        if (!this.owned) {
//            entity.setOwned(this.owned);
//        }
//        if (!this.wish) {
//            entity.setWish(this.wish);
//        }
//        if (this.quantity != null) {
//            entity.setQuantity(this.quantity);
//        }
//        if (this.formato != null) {
//            entity.setFormato(this.formato);
//        }
//        if (this.ratchet != null) {
//            entity.setRatchet(this.ratchet);
//        }
//        if (this.punta != null) {
//            entity.setPunta(this.punta);
//        }
//
//    }
}
