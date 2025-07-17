package org.progetto.dto.punte;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.tipologiche.TipoBeybladePunte;

@Getter
@Setter
public class TipoBeybladePunteResultDTO {

    private Long id;
    private String namePoint;
    private Double weight;
    private String originBeyFrom;
    private String formato;
    private Boolean owned;

    public static TipoBeybladePunteResultDTO fromEntity(TipoBeybladePunte entity){
        TipoBeybladePunteResultDTO dto = new TipoBeybladePunteResultDTO();
        dto.setId(entity.getId());
        dto.setNamePoint(entity.getNamePoint());
        dto.setWeight(entity.getWeight());
        dto.setOriginBeyFrom(entity.getOriginBeyFrom());
        dto.setOwned(entity.isOwned());
        return dto;
    }
}
