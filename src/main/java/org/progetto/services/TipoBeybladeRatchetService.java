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
import org.progetto.dto.SearchTipoBeybladeRatchetRequestDTO;
import org.progetto.dto.TipoBeybladeRatchetInsertDTO;
import org.progetto.dto.TipoBeybladeRatchetResultDTO;
import org.progetto.exception.ServiceException;
import org.progetto.model.tipologiche.TipoBeybladeRatchet;

@ApplicationScoped
public class TipoBeybladeRatchetService {

    @Inject
    Database db;

    public void insertTipoBeybladeRatchet(TipoBeybladeRatchetInsertDTO dto){
        try(Transaction tx = db.beginTransaction()){
            db.save(TipoBeybladeRatchetInsertDTO.fromDto(dto));
            tx.commit();
        }
        catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public TipoBeybladeRatchetResultDTO findTipoRatchetById(Integer id){
        TipoBeybladeRatchet tipoBeybladeRatchet = db.find(TipoBeybladeRatchet.class)
                .where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new EntityNotFoundException("Nessun Ratchet trovato con id " + id));
        return TipoBeybladeRatchetResultDTO.fromEntity(tipoBeybladeRatchet);
    }

    public PagedResultDTO<TipoBeybladeRatchetResultDTO> getTipiRatchet(SearchTipoBeybladeRatchetRequestDTO request){
        ExpressionList<TipoBeybladeRatchet> exl = db.find(TipoBeybladeRatchet.class)
                        .where();
        if(request.getBumps() != null)
            exl.eq("Bumps", request.getBumps());
        if(request.getHeight() != null)
            exl.eq("Height", request.getHeight());
        if(StringUtils.isNotBlank(request.getOriginBeyFrom()))
            exl.ilike("originBeyFrom",request.getOriginBeyFrom() + "%");
        request.applySortAndPagination(exl);
        PagedList<TipoBeybladeRatchet> pl = exl.findPagedList();
        pl.loadCount();
        return PagedResultDTO.of(pl, TipoBeybladeRatchetResultDTO::fromEntity);

    }
}
