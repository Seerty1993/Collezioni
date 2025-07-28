package org.progetto.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.progetto.dto.PagedResultDTO;
import org.progetto.dto.ResponseEntity;
import org.progetto.dto.punte.SearchTipoBeybladePunteRequestDTO;
import org.progetto.dto.punte.TipoBeybladePunteRequestDTO;
import org.progetto.dto.punte.TipoBeybladePunteResultDTO;
import org.progetto.services.TipoBeybladePunteService;

@Path("/api/v1/tipoPunte")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Beyblade - API Tipo Punte")
@Slf4j
public class TipoBeybladePunteResource {

    @Inject
    TipoBeybladePunteService tipoBeybladePunteService;


    @POST
    @Operation(summary = "Insert Punte", description = "API per l'inserimento delle punte")
    public ResponseEntity<Void> insertTipoBeybladePunte(TipoBeybladePunteRequestDTO request) {
        log.info("TipoBeybladePunteResource - insertTipoBeybladePunte");
        tipoBeybladePunteService.insertTipoBeybladePunte(request);
        return new ResponseEntity<Void>().addMessage("Punte inserite con successo!");
    }


    @GET
    @Path("{id}")
    @Operation(summary = "Get Punte by id", description = "API per la restituzione di una punta tramite id")
    public TipoBeybladePunteResultDTO findTipoPunteDetails(@PathParam("id") Long id) {
        log.info("TipoBeybladePunteResource - findTipoPunteDetails");
        return tipoBeybladePunteService.findTipoPunteDetails(id);
    }


    @GET
    @Operation(summary = "Find All Punte", description = "API per la restituzione della lista delle punte")
    public PagedResultDTO<TipoBeybladePunteResultDTO> getTipiPunte(@BeanParam SearchTipoBeybladePunteRequestDTO request) {
        log.info("TipoBeybladePunteResource - getTipiPunte");
        return tipoBeybladePunteService.getTipiPunte(request);
    }


    @PUT
    @Path("{id}")
    @Operation(summary = "Update Punte", description = "API per l'update delle punte")
    public ResponseEntity<Void> updateTipoBeybladePunte(@PathParam("id") Long id, TipoBeybladePunteRequestDTO request) {
        log.info("TipoBeybladePunteResource - updateTipoBeybladePunte");
        tipoBeybladePunteService.updateTipoBeybladePunte(id, request);
        return new ResponseEntity<Void>().addMessage("Punte inserite con successo!");
    }
}