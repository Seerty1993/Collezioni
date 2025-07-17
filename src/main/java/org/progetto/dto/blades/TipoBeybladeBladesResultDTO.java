package org.progetto.dto.blades;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.tipologiche.TipoBeybladeBlades;

@Getter
@Setter
public class TipoBeybladeBladesResultDTO {

    private Long id;
    private String spin;
    private String type;
    private String formato;
    private Double weight;
    private String nameBlades;
    private Boolean owned;


    public static TipoBeybladeBladesResultDTO fromEntity(TipoBeybladeBlades entity){
        TipoBeybladeBladesResultDTO dto = new TipoBeybladeBladesResultDTO();
        dto.setId(entity.getId());
        dto.setSpin(String.valueOf(entity.getSpin()));
        dto.setType(String.valueOf(entity.getType()));
        dto.setFormato(String.valueOf(entity.getFormato()));
        dto.setWeight(entity.getWeight());
        dto.setNameBlades(entity.getNameBlades());
        dto.setOwned(entity.isOwned());
        return dto;
    }
}
