package org.progetto.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.progetto.dto.*;
import org.progetto.services.BeybladeService;

import java.util.List;

@Tag(name = "Beyblades")
@Path("beyblades")
public class BeybladeResource {

    @Inject
    BeybladeService beybladeService;

    @GET
    @Path("/find-all")
    @Operation(summary = "findAllBeyblades",
            description = "Trova tutti i beyblades")
    public List<BeybladeDetailDTO> findAllBeyblades() {
        return beybladeService.findAllBeyblades();
    }

    @GET
    @Path("/find-id/{id}")
    @Operation(summary = "findBeybladeById",
            description = "Trova un beyblade specifico per id")
    public BeybladeDetailDTO findBeybladeById(@PathParam("id") Integer id) {
        return beybladeService.findBeybladeById(id);
    }

    @GET
    @Path("/find-filter")
    @Operation(summary = "findBeybladeByFilter",
            description = "Trova un beyblade specifico per filtro")
    public PagedResultDTO<BeybladeDetailDTO> findBeybladeByFilter(@BeanParam BeybladeSearchRequest bey) {
        return beybladeService.findBeybladeByFilter(bey);
    }


//    @POST
//    @Path("/add")
//    @Operation(summary = "addBeybladeWithComponents",
//            description = "Aggiungi un nuovo beyblade con tutti i componenti nel dettaglio")
//    public SimpleResultDTO<BeybladeDTO> addBeybladeWithComponents(@Valid BeybladeDTO beyblade) {
//
//        return SimpleResultDTO.<BeybladeDTO>builder()
//                .payload(beybladeService.addBeybladeWithComponents(beyblade))
//                .message(beyblade.getName() + " " + beyblade.getRatchet().getName_ratchet() + " "
//                        + beyblade.getPunta().getNamePoint() + " aggiunto con successo")
//                .build();
//    }


    @POST
    @Path("/add")
    @Operation(summary = "addBeyblade",
            description = "Aggiungi un nuovo beyblade")
    public SimpleResultDTO<BeybladeDTO> addBeyblade(@Valid BeybladeDTO beyblade) {

        return SimpleResultDTO.<BeybladeDTO>builder()
                .payload(beybladeService.addBeyblade(beyblade))
                .message(beyblade.getName() + " " + beyblade.getRatchet() + " "
                        + beyblade.getPunta() + " aggiunto con successo")
                .build();
    }


}
