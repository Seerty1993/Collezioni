package org.progetto.dto;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.enumModel.BeybladeFormato;
import org.progetto.model.enumModel.BeybladeSpin;
import org.progetto.model.enumModel.BeybladeType;
import org.progetto.model.tipologiche.TipoBeybladeBlades;

@Getter
@Setter
public class TipoBeybladeBladesRequestDTO {

    private String spin;
    private String type;
    private Double weight;
    private String nameBlades;
    private String formato;
    private Boolean owned;


    public TipoBeybladeBlades toEntity(){
        TipoBeybladeBlades entity = new TipoBeybladeBlades();
        entity.setSpin(BeybladeSpin.valueOf(this.getSpin()));
        entity.setType(BeybladeType.valueOf(this.getType()));
        entity.setWeight(this.getWeight());
        entity.setNameBlades(this.getNameBlades());
        entity.setFormato(BeybladeFormato.valueOf(this.formato));
        updateEntity(entity);
        return entity;
    }

    public void updateEntity(TipoBeybladeBlades entity){
        entity.setOwned(this.getOwned());
    }
}
