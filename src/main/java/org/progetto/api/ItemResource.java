//package org.progetto.api;
//
//import ai.onnxruntime.OrtSession;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.MediaType;
//import org.eclipse.microprofile.openapi.annotations.Operation;
//import org.jboss.resteasy.reactive.RestForm;
//import io.ebean.*;
//import org.progetto.DetectImage.DetectedObject;
//import org.progetto.DetectImage.Postprocessor;
//import org.progetto.DetectImage.Preprocessor;
//import org.progetto.model.Item;
//import org.progetto.services.DetectionService;
//
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.io.InputStream;
//
//@Path("/items")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.MULTIPART_FORM_DATA)
//public class ItemResource {
//
//    @Inject
//    DetectionService detector;
//
//    public static class UploadForm {
//        @RestForm("file")
//        public InputStream file;
//        @RestForm("fileName")
//        public String fileName;
//    }
//
//    @POST
//    @Operation(summary = "upload and detect", description = "Carica e analizza l'immagine")
//    public Item uploadAndDetect(UploadForm form) throws Exception {
//        // 1) Carica BufferedImage
//        BufferedImage img = ImageIO.read(form.file);
//
//        // 2) Preprocess → tensor float[1][3][640][640]
//        float[][][][] tensor = Preprocessor.toTensor(img, 640, 640);
//
//        // 3) Inferenza
//        OrtSession.Result results = detector.detect(tensor);
//
//        // 4) Postprocess estrai label + confidence
//        DetectedObject obj = Postprocessor.extract(results, 0.4f);
//
//        // 5) Salva con Ebean
//        Item item = new Item();
//        item.name = obj.getLabel();
//        item.confidence = obj.getConfidence();
//        item.save();
//
//        return item;
//    }
//}
