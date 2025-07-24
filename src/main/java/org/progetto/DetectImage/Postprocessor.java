package org.progetto.DetectImage;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for postprocessing ONNX model outputs.
 */
public class Postprocessor {
    // Soglia di confidenza minima
    private static final float CONF_THRESHOLD = 0.5f;

    public static List<DetectedObject> topK(List<DetectedObject> list, int k) {
        return list.stream()
                .sorted((a, b) -> Double.compare(b.getConfidence(), a.getConfidence()))
                .limit(k)
                .collect(Collectors.toList());
    }

    // Sostituisci con le etichette del tuo modello (nell’ordine corretto)
    private static final String[] LABELS = {
            "person", "bicycle", "car", "motorcycle", "airplane", "bus", "train", "truck", "boat",
            "traffic light", "fire hydrant", "stop sign", "parking meter", "bench", "bird", "cat",
            "dog", "horse", "sheep", "cow", "elephant", "bear", "zebra", "giraffe", "backpack",
            "umbrella", "handbag", "tie", "suitcase", "frisbee", "skis", "snowboard", "sports ball",
            "kite", "baseball bat", "baseball glove", "skateboard", "surfboard", "tennis racket",
            "bottle", "wine glass", "cup", "fork", "knife", "spoon", "bowl", "banana", "apple",
            "sandwich", "orange", "broccoli", "carrot", "hot dog", "pizza", "donut", "cake", "chair",
            "couch", "potted plant", "bed", "dining table", "toilet", "tv", "laptop", "mouse", "remote",
            "keyboard", "cell phone", "microwave", "oven", "toaster", "sink", "refrigerator", "book",
            "clock", "vase", "scissors", "teddy bear", "hair drier", "toothbrush"
    };


    /**
     * Estrae il rilevamento con confidenza più alta sopra threshold.
     * Assumiamo output [1][N][6]: [x, y, w, h, conf, classIdx]
     *
     * @param result    risultato della sessione ONNX
     * @param threshold soglia di confidenza
     * @return DetectedObject (label + confidence)
     * @throws OrtException
     */
    public static DetectedObject extract(OrtSession.Result result, float threshold) throws OrtException {
        // Prendi il primo OnnxValue
        OnnxTensor tensor = (OnnxTensor) result.get(0);
        float[][][] output = (float[][][]) tensor.getValue();

        float bestConf = threshold;
        int bestClass = -1;
        for (float[] det : output[0]) {
            float conf = det[4];
            if (conf > bestConf) {
                bestConf = conf;
                bestClass = (int) det[5];
            }
        }

        if (bestClass >= 0 && bestClass < LABELS.length) {
            return new DetectedObject(LABELS[bestClass], bestConf);
        }
        return new DetectedObject("unknown", 0.0);
    }

    public static List<DetectedObject> extractAll(OrtSession.Result result, float threshold) throws OrtException {
        OnnxTensor tensor = (OnnxTensor) result.get(0);
        float[][][] output = (float[][][]) tensor.getValue();
        List<DetectedObject> detections = new ArrayList<>();

        for (float[] det : output[0]) {
            float objectness = det[4];
            // find class index and probability
            int numClasses = det.length - 5;
            float maxClassProb = 0;
            int classIndex = -1;
            for (int c = 0; c < numClasses; c++) {
                float classProb = det[5 + c];
                if (classProb > maxClassProb) {
                    maxClassProb = classProb;
                    classIndex = c;
                }
            }
            // combined confidence
            float confidence = objectness * maxClassProb;
            if (confidence >= threshold && classIndex >= 0 && classIndex < LABELS.length) {
                detections.add(new DetectedObject(LABELS[classIndex], confidence));
            }
        }
        return detections;
    }

    public static DetectedObject extractOne(OrtSession.Result result, float threshold) throws OrtException {
        List<DetectedObject> all = extractAll(result, threshold);
        return all.stream()
                .max(Comparator.comparingDouble(DetectedObject::getConfidence))
                .orElse(new DetectedObject("unknown", 0.0));
    }

}
