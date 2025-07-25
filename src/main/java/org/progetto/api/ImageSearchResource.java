package org.progetto.api;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.progetto.services.ImageSearchService;

import java.io.IOException;

@Path("/image")
public class ImageSearchResource {

    @Inject
    ImageSearchService searchService;

    @PostConstruct
    void init() throws IOException {
        searchService.loadCatalog("src/main/resources/catalogo");
    }

    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public String search(byte[] queryImg) throws IOException {
        return searchService.findBestMatch(queryImg);
    }
}
