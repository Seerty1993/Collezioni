package org.progetto.DetectImage;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Rappresenta un oggetto rilevato con label e confidence.
 */
@Getter
public class DetectedObject {
    private final String label;
    private final double confidence;

    public DetectedObject(String label, double confidence) {
        this.label = label;
        this.confidence = confidence;
    }

}

