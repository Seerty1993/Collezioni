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
import org.progetto.dto.punte.SearchTipoBeybladePunteRequestDTO;
import org.progetto.dto.punte.TipoBeybladePunteRequestDTO;
import org.progetto.dto.punte.TipoBeybladePunteResultDTO;
import org.progetto.exception.ServiceException;
import org.progetto.model.tipologiche.TipoBeybladePunte;

@ApplicationScoped
public class TipoBeybladePunteService {

    @Inject
    Database db;

    public void insertTipoBeybladePunte(TipoBeybladePunteRequestDTO request){
        try(Transaction tx = db.beginTransaction()){
            TipoBeybladePunte tipoBeybladePunte = request.toEntity();
            db.save(tipoBeybladePunte);
            tx.commit();
        }
        catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public TipoBeybladePunte getTipoBeybladePunteById(Long id){
        return db.find(TipoBeybladePunte.class)
                .where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new EntityNotFoundException("Nessuna punta trovata con id: " + id));
    }


    public TipoBeybladePunteResultDTO findTipoPunteDetails(Long id){
        TipoBeybladePunte tipoBeybladePunte = getTipoBeybladePunteById(id);
        return TipoBeybladePunteResultDTO.fromEntity(tipoBeybladePunte);
    }


    public PagedResultDTO<TipoBeybladePunteResultDTO> getTipiPunte(SearchTipoBeybladePunteRequestDTO request){
        ExpressionList<TipoBeybladePunte> exl = db.find(TipoBeybladePunte.class)
                        .where();
        if(StringUtils.isNotBlank(request.getNamePoint()))
            exl.eq("namePoint", request.getNamePoint());
        if(request.getWeight() != null)
            exl.eq("weight", request.getWeight());
        if(StringUtils.isNotBlank(request.getOriginBeyFrom()))
            exl.eq("originBeyFrom", request.getOriginBeyFrom());
        if(request.getOwned() != null)
            exl.eq("owned", request.getOwned());
        request.applySortAndPagination(exl);
            PagedList<TipoBeybladePunte> pl = exl.findPagedList();
        pl.loadCount();
        return PagedResultDTO.of(pl, TipoBeybladePunteResultDTO::fromEntity);
    }


    public void updateTipoBeybladePunte(Long id, TipoBeybladePunteRequestDTO dto){
        try(Transaction tx = db.beginTransaction()){
            TipoBeybladePunte tipoBeybladePunte = getTipoBeybladePunteById(id);
            dto.updateEntity(tipoBeybladePunte);
            db.update(tipoBeybladePunte);
            tx.commit();
        }
        catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
