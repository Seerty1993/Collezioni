package org.progetto;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

import java.io.IOException;


public class GenerateDbMigration {

    public static void main(String[] args) throws IOException {
        DbMigration d = DbMigration.create();
        d.setPathToResources(System.getProperty("user.dir") + "/Collezioni/src/main/resources");
        d.addPlatform(Platform.POSTGRES);
        d.setVersion("V1.0");
        d.setName("schema");
        d.generateInitMigration();
    }
}