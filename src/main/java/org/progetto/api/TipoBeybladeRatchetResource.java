package org.progetto.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.progetto.dto.*;
import org.progetto.dto.ratchet.SearchTipoBeybladeRatchetRequestDTO;
import org.progetto.dto.ratchet.TipoBeybladeRatchetRequestDTO;
import org.progetto.dto.ratchet.TipoBeybladeRatchetResultDTO;
import org.progetto.services.TipoBeybladeRatchetService;

@Path("/api/v1/tipoRatchet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Beyblade - API Tipo Ratchet")
@Slf4j
public class TipoBeybladeRatchetResource {

    @Inject
    TipoBeybladeRatchetService tipoBeybladeRatchetService;

    @POST
    @Operation(summary = "Insert Ratchet", description = "API per l'inserimento del ratchet")
    public ResponseEntity<Void> insertTipoBeybladeRatchet(TipoBeybladeRatchetRequestDTO dto) {
        log.info("TipoBeybladeRatchetResource - insertTipoBeybladeRatchet");
        tipoBeybladeRatchetService.insertTipoBeybladeRatchet(dto);
        return new ResponseEntity<Void>().addMessage("Ratchet inserito con successo");
    }


    @GET
    @Path("{id}")
    @Operation(summary = "Get Ratchet by id", description = "API per la restituzione di un ratchet tramite id")
    public TipoBeybladeRatchetResultDTO findTipoRatchetDetails(@PathParam("id") Long id) {
        log.info("TipoBeybladeRatchetResource - findTipoRatchetDetails");
        return tipoBeybladeRatchetService.findTipoRatchetDetails(id);
    }


    @GET
    @Path("find-all-ratchet")
    @Operation(summary = "Find All Ratchet", description = "API per la restituzione della lista dei ratchet")
    public PagedResultDTO<TipoBeybladeRatchetResultDTO> getTipiRatchet(@BeanParam SearchTipoBeybladeRatchetRequestDTO request) {
        log.info("TipoBeybladeRatchetResource - getTipiRatchet");
        return tipoBeybladeRatchetService.getTipiRatchet(request);
    }


    @PUT
    @Path("{id}")
    @Operation(summary = "Update Ratchet", description = "API per l'update dei ratchet")
    public ResponseEntity<Void> updateTipoBeybladeRatchet(@PathParam("id") Long id, TipoBeybladeRatchetRequestDTO request) {
        log.info("TipoBeybladeRatchetResource - updateTipoBeybladeRatchet");
        tipoBeybladeRatchetService.updateTipoBeybladeRatchet(id, request);
        return new ResponseEntity<Void>().addMessage("Ratchet aggiornato con successo!");
    }
}
