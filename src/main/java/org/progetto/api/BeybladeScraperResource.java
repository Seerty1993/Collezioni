package org.progetto.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.progetto.dto.ResponseEntity;
import org.progetto.services.BeybladeScraperService;

@Path("/api/v1/scraper")
@Tag(name = "Api di Scraper")
@Slf4j
public class BeybladeScraperResource {
    @Inject
    BeybladeScraperService beybladeScraperService;

    @GET
    @Path("/beyblade")
    @Operation(description = "Api di scraper per i Beyblade")
    public ResponseEntity<Void> fetchAndSave() {
        log.info("BeybladeScraperResource - fetchAndSave");
        beybladeScraperService.fetchAndSave();
        return new ResponseEntity<Void>().addMessage("Scraper riuscito, puoi trovare il file in Json nel percorso: " + beybladeScraperService.getFilePath());
    }

    public ResponseEntity<Void> InsertJsonBeyblade() {
        return new ResponseEntity<Void>().addMessage("");
    }

}
