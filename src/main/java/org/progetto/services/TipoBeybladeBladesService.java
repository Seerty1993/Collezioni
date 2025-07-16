package org.progetto.services;

import io.ebean.Database;
import io.ebean.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.progetto.dto.TipoBeybladeBladesRequestDTO;
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
}
