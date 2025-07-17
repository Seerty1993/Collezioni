package org.progetto.services;

import io.ebean.Database;
import io.ebean.ExpressionList;
import io.ebean.PagedList;
import io.ebean.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.progetto.dto.PagedResultDTO;
import org.progetto.dto.blades.SearchTipoBeybladeBladesRequestDTO;
import org.progetto.dto.blades.TipoBeybladeBladesRequestDTO;
import org.progetto.dto.blades.TipoBeybladeBladesResultDTO;
import org.progetto.exception.ServiceException;
import org.progetto.model.tipologiche.TipoBeybladeBlades;

@ApplicationScoped
public class TipoBeybladeBladesService {

    @Inject
    Database db;

    public void insertBeybladeBlades(TipoBeybladeBladesRequestDTO request){
        try(Transaction tx = db.beginTransaction()){
            TipoBeybladeBlades tipoBeybladeBlades = request.toEntity();
            db.save(tipoBeybladeBlades);
            tx.commit();
        }
        catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public TipoBeybladeBlades getTipoBeybladeBladesById(Integer id){
        return db.find(TipoBeybladeBlades.class)
                .where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new EntityNotFoundException("Nessuna blade trovata con id: " + id));
    }


    public TipoBeybladeBladesResultDTO findTipoBladesDetails(Integer id){
        TipoBeybladeBlades blades = getTipoBeybladeBladesById(id);
        return TipoBeybladeBladesResultDTO.fromEntity(blades);
    }


    public PagedResultDTO<TipoBeybladeBladesResultDTO> getTipiBlades(SearchTipoBeybladeBladesRequestDTO request){
        ExpressionList<TipoBeybladeBlades> exl = db.find(TipoBeybladeBlades.class)
                        .where();
        if(StringUtils.isNotBlank(request.getSpin()))
            exl.eq("spin", request.getSpin());
        if(StringUtils.isNotBlank(request.getType()))
            exl.eq("type", request.getType());
        if(StringUtils.isNotBlank(request.getFormato()))
            exl.eq("formato", request.getFormato());
        if(request.getWeight() != null)
            exl.eq("weight", request.getWeight());
        if(StringUtils.isNotBlank(request.getNameBlades()))
            exl.eq("nameBlades", request.getNameBlades());
        if(request.getOwned() != null)
            exl.eq("owned", request.getOwned());
        request.applySortAndPagination(exl);
        PagedList<TipoBeybladeBlades> pl = exl.findPagedList();
        pl.loadCount();
        return PagedResultDTO.of(pl, TipoBeybladeBladesResultDTO::fromEntity);
    }


    public void updateTipoBeybladeBlades(Integer id, TipoBeybladeBladesRequestDTO request){
        try(Transaction tx = db.beginTransaction()){
            TipoBeybladeBlades tipoBeybladeBlades = getTipoBeybladeBladesById(id);
            request.updateEntity(tipoBeybladeBlades);
            db.update(tipoBeybladeBlades);
            tx.commit();
        }
        catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
