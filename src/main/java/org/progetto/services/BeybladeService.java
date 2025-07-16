package org.progetto.services;

import io.ebean.Database;
import io.ebean.ExpressionList;
import io.ebean.PagedList;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.progetto.dto.*;
import org.progetto.exception.ServiceException;
import org.progetto.model.Beyblade;
import org.progetto.model.Category;

import java.util.List;

@ApplicationScoped
public class BeybladeService {
    @Inject
    Database db;

    public List<BeybladeDetailDTO> findAllBeyblades() {
        List<Beyblade> beyblades = db.find(Beyblade.class).findList();
        try {
            return beyblades.stream()
                    .map(BeybladeDetailDTO::of)
                    .toList();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage() + "Nessun beyblade presente");
        }
    }

    public BeybladeDetailDTO findBeybladeById(Integer id) {
        Beyblade entity = db.find(Beyblade.class)
                .where()
                .eq("id", id)
                .findOneOrEmpty()
                .orElseThrow(() -> new ServiceException("Beyblade non trovato"));

        return BeybladeDetailDTO.of(entity);
    }

    public PagedResultDTO<BeybladeDetailDTO> findBeybladeByFilter(BeybladeSearchRequest bey) {
        ExpressionList<Beyblade> query = db.find(Beyblade.class).where();
        PagedList<Beyblade> BeyResultList = bey.filterBuilder(query).findPagedList();
        return PagedResultDTO.of(BeyResultList, BeybladeDetailDTO::of);
    }


    public BeybladeDTO addBeyblade(BeybladeDTO beyblade) {
        try {
            db.insert(beyblade.toEntity());
            return beyblade;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage() + " Beyblade già presente o dati non validi");
        }
    }


}
