package org.progetto.dto;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

@Getter
@Setter
public class TipoBeybladeRatchetRequestDTO {

    private Integer bumps;
    private Integer height;
    private Double weight;
    private String originBeyFrom;
    private Boolean owned;

    public TipoBeybladeRatchet toEntity(){
        TipoBeybladeRatchet entity = new TipoBeybladeRatchet();
        entity.setBumps(this.getBumps());
        entity.setHeight(this.getHeight());
        entity.setWeight(this.getWeight());
        entity.setOriginBeyFrom(this.getOriginBeyFrom());
        entity.setNameRatchet(this.getBumps().toString() + "-" + this.getHeight().toString());
        entity.setOwned(this.getOwned());
        updateEntity(entity);
        return entity;
    }

    public void updateEntity(TipoBeybladeRatchet entity){
        entity.setOwned(this.getOwned());
    }
}
