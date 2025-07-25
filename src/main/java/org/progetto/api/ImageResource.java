package org.progetto.api;

import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;
import org.progetto.services.ImageComparatorService;

import java.nio.file.Files;

@Path("/image")
public class ImageResource {

    private final ImageComparatorService imageComparator;

    public ImageResource(ImageComparatorService imageComparator) {
        this.imageComparator = imageComparator;
    }


    @POST
    @Path("/compare")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String compare(
            @RestForm("localImage") FileUpload local,
            @RestForm("uploadedImage") FileUpload uploaded) throws Exception {

        byte[] img1 = Files.readAllBytes(local.filePath());
        byte[] img2 = Files.readAllBytes(uploaded.filePath());
        double similarity = imageComparator.compareImages(img1, img2);
        String name = FilenameUtils.getBaseName(local.fileName());
        return "Dovrebbe esse l'oggetto di nome: " + name + ", con una similarità del " + (similarity * 100) + "%";
    }
}


