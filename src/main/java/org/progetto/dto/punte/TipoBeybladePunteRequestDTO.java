package org.progetto.dto.punte;

import lombok.Getter;
import lombok.Setter;
import org.progetto.model.enumModel.BeybladeFormato;
import org.progetto.model.tipologiche.TipoBeybladePunte;

@Getter
@Setter
public class TipoBeybladePunteRequestDTO {

    private Integer id;
    private String namePoint;
    private Double weight;
    private String originBeyFrom;
    private String formato;
    private Boolean owned;

    public TipoBeybladePunte toEntity(){
        TipoBeybladePunte entity = new TipoBeybladePunte();
        entity.setNamePoint(this.getNamePoint());
        entity.setWeight(this.getWeight());
        updateEntity(entity);
        return entity;
    }


    public void updateEntity(TipoBeybladePunte entity){
        entity.setOwned(this.getOwned());
        entity.setOriginBeyFrom(this.getOriginBeyFrom());
        entity.setFormato(BeybladeFormato.valueOf(this.getFormato()));
    }
}
