package org.progetto.model;


import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;

import java.io.InputStream;


public class ImageUploadForm {

    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream localImage;

    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream uploadedImage;
}

