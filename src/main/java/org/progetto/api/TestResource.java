//package org.progetto.api;
//
//import ai.onnxruntime.OrtSession;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.GET;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.WebApplicationException;
//import jakarta.ws.rs.core.MediaType;
//import org.progetto.DetectImage.DetectedObject;
//import org.progetto.DetectImage.Postprocessor;
//import org.progetto.DetectImage.Preprocessor;
//import org.progetto.dto.DetectItem.TestAllResultDTO;
//import org.progetto.services.DetectionService;
//
//import java.awt.image.BufferedImage;
//import java.io.InputStream;
//import java.util.List;
//import javax.imageio.ImageIO;
//
//@Path("/items/test")
//@Produces(MediaType.APPLICATION_JSON)
//@ApplicationScoped
//public class TestResource {
//
//    @Inject
//    DetectionService detector;
//
//    @GET
//    public TestResult runTest() throws Exception {
//        // 1) Carica immagine di test dal classpath
//        try (InputStream is = getClass().getResourceAsStream("/test-data/foto_tessera.jpg")) {
//            if (is == null) {
//                throw new WebApplicationException("Immagine di test non trovata!", 404);
//            }
//            BufferedImage img = ImageIO.read(is);
//            float[][][][] tensor = Preprocessor.toTensor(img, 640, 640);
//            OrtSession.Result results = detector.detect(tensor);
//
/// /            List<DetectedObject> all = Postprocessor.extractAll(results, 0.4f);
/// /            List<DetectedObject> top3 = Postprocessor.topK(all, 3);
/// /
/// /            return new TestAllResultDTO(top3);
//            DetectedObject top = Postprocessor.extractOne(results, 0.4f);
//            return new TestResult(top.getLabel(), top.getConfidence());
//
//        }
//    }
//
//    public static class TestResult {
//        public String label;
//        public double confidence;
//
//        public TestResult() {
//        }
//
//        public TestResult(String label, double confidence) {
//            this.label = label;
//            this.confidence = confidence;
//        }
//    }
//}
