package org.progetto.dto;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

@Getter
@Setter
public class TipoBeybladeRatchetInsertDTO {

    private Integer bumps;
    private Integer height;
    private Double weight;
    private String originBeyFrom;
    private Boolean owned;

    public static TipoBeybladeRatchet fromDto(TipoBeybladeRatchetInsertDTO dto){
        TipoBeybladeRatchet entity = new TipoBeybladeRatchet();
        entity.setBumps(dto.getBumps());
        entity.setHeight(dto.getHeight());
        entity.setWeight(dto.getWeight());
        entity.setOriginBeyFrom(dto.getOriginBeyFrom());
        entity.setNameRatchet(dto.getBumps().toString() + "-" + dto.getHeight().toString());
        entity.setOwned(dto.getOwned());
        return entity;
    }
}
