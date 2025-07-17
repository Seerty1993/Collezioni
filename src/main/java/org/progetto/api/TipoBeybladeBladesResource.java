package org.progetto.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.progetto.dto.PagedResultDTO;
import org.progetto.dto.ResponseEntity;
import org.progetto.dto.blades.SearchTipoBeybladeBladesRequestDTO;
import org.progetto.dto.blades.TipoBeybladeBladesRequestDTO;
import org.progetto.dto.blades.TipoBeybladeBladesResultDTO;
import org.progetto.services.TipoBeybladeBladesService;

@Path("/api/v1/tipoBlades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "API Tipo Blades")
@Slf4j
public class TipoBeybladeBladesResource {

    @Inject
    TipoBeybladeBladesService tipoBeybladeBladesService;

    @POST
    @Operation(summary = "Insert Blades", description = "API per l'inserimento delle blades")
    public ResponseEntity<Void> insertBeybladeBlades(TipoBeybladeBladesRequestDTO request){
        log.info("TipoBeybladeBladesResource - insertBeybladeBlades");
        tipoBeybladeBladesService.insertBeybladeBlades(request);
        return new ResponseEntity<Void>().addMessage("Blades inserite con successo");
    }


    @GET
    @Path("{id}")
    @Operation(summary = "Get Blades by id", description = "API per la restituzione di una blade tramite id")
    public TipoBeybladeBladesResultDTO findTipoBladesDetails(@PathParam("id") Integer id){
        log.info("TipoBeybladeBladesResource - findTipoBladesDetails");
        return tipoBeybladeBladesService.findTipoBladesDetails(id);
    }


    @GET
    @Operation(summary = "Find All Blades", description = "API per la restituzione della lista dei blades")
    public PagedResultDTO<TipoBeybladeBladesResultDTO> getTipiBlades(@BeanParam SearchTipoBeybladeBladesRequestDTO request) {
        log.info("TipoBeybladeBladesResource - getTipiBlades");
        return tipoBeybladeBladesService.getTipiBlades(request);
    }


    @PUT
    @Path("{id}")
    @Operation(summary = "Update Blades", description = "API per l'update delle blades")
    public ResponseEntity<Void> updateTipoBeybladeBlades(@PathParam("id") Integer id, TipoBeybladeBladesRequestDTO request) {
        log.info("TipoBeybladeBladesResource - updateTipoBeybladeBlades");
        tipoBeybladeBladesService.updateTipoBeybladeBlades(id, request);
        return new ResponseEntity<Void>().addMessage("Blade aggiornata con successo!");
    }

}
