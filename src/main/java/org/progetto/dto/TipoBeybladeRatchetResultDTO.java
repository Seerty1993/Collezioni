package org.progetto.dto;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

@Getter
@Setter
public class TipoBeybladeRatchetResultDTO {

    private Integer id;
    private String nameRatchet;
    private Integer bumps;
    private Integer height;
    private Double weight;
    private String originBeyFrom;
    private Boolean owned;

    public static TipoBeybladeRatchetResultDTO fromEntity(TipoBeybladeRatchet entity){
        TipoBeybladeRatchetResultDTO dto = new TipoBeybladeRatchetResultDTO();
        dto.setId(entity.getId());
        dto.setNameRatchet(entity.getNameRatchet());
        dto.setBumps(entity.getBumps());
        dto.setHeight(entity.getHeight());
        dto.setWeight(entity.getWeight());
        dto.setOriginBeyFrom(entity.getOriginBeyFrom());
        dto.setOwned(entity.getOwned());
        return dto;
    }
}
