package org.progetto.services;


import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@ApplicationScoped
@Slf4j
public class DetectionService {

    private OrtSession session;

    @PostConstruct
    public void init() {
        try {
            // 1) individua il resource nel classpath
            URL res = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource("models/yolov5s.onnx");
            if (res == null) {
                throw new IllegalStateException("Modello ONNX non trovato in /models/yolov5s.onnx");
            }
            Path modelPath = Paths.get(res.toURI());

            // 2) setup ONNX Runtime
            OrtEnvironment env = OrtEnvironment.getEnvironment();
            OrtSession.SessionOptions opts = new OrtSession.SessionOptions();
            opts.setInterOpNumThreads(8);
            opts.setIntraOpNumThreads(1);

            // 3) crea la session
            session = env.createSession(modelPath.toString(), opts);
            log.info("DetectionService inizializzato correttamente");
        } catch (Exception e) {
            log.error("Errore in DetectionService.init()", e);
            // rilancia come runtime exception per far vedere subito l’errore
            throw new RuntimeException("Impossibile inizializzare DetectionService: "
                    + e.getClass().getSimpleName() + " – " + e.getMessage(), e);
        }
    }

    public OrtSession.Result detect(float[][][][] inputTensor) throws OrtException {
        OnnxTensor tensor = OnnxTensor.createTensor(
                OrtEnvironment.getEnvironment(), inputTensor);
        return session.run(Collections.singletonMap("images", tensor));
    }
}

//@ApplicationScoped
//public class DetectionService {
//
//    private OrtEnvironment env;
//    private OrtSession session;
//
//    @PostConstruct
//    public void init() throws OrtException {
//        env = OrtEnvironment.getEnvironment();
//        OrtSession.SessionOptions opts = new OrtSession.SessionOptions();
/// /        opts.addCPU();               // CPU EP
/// /        opts.addOpenVINO();         // OpenVINO EP (richiede OpenVINO installato)
//        opts.setInterOpNumThreads(8);
//        opts.setIntraOpNumThreads(1);
//
//        session = env.createSession(
//                Paths.get("model/yolov5s.onnx").toString(),
//                opts
//        );
//    }
//
//    public OrtSession.Result detect(float[][][][] inputTensor) throws OrtException {
//        // crea input tensor (1x3x640x640) … qui devi preprocessare l’immagine
//        OnnxTensor tensor = OnnxTensor.createTensor(env, inputTensor);
//        return session.run(Collections.singletonMap("images", tensor));
//    }
//}
