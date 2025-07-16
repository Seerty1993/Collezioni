package org.progetto.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.agroal.api.AgroalDataSource;
import io.ebean.Database;
import io.ebean.config.DatabaseConfig;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import java.sql.SQLException;


public class EbeanConfig {


    @ApplicationScoped
    @Startup
    @Produces
    public Database createDb(AgroalDataSource source, ObjectMapper mapper) {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setDataSource(source);

        // Per far generare automaticamente le tabelle da Ebean
//        dbConfig.setDdlGenerate(true);
//        dbConfig.setDdlRun(true);
//        dbConfig.setDdlCreateOnly(true);


        dbConfig.setObjectMapper(mapper);
        dbConfig.setIdGeneratorAutomatic(false);
        dbConfig.setDefaultServer(true);
        dbConfig.addPackage("org.progetto.model");


        return dbConfig.build();
    }

    @Singleton
    public ObjectMapper createMapper() {
        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());
        obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        obj.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return obj;
    }
}
