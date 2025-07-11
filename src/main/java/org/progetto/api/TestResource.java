package org.progetto.api;

import io.ebean.DB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.progetto.model.TestEntity;

@Path("/test")
public class TestResource {

    @GET
    @Path("/connection")
    public String testConnection() {
        try {
            // Test connessione database
            DB.getDefault().createQuery(TestEntity.class).findCount();
            return "Connessione OK";
        } catch (Exception e) {
            return "Errore: " + e.getMessage();
        }
    }
}