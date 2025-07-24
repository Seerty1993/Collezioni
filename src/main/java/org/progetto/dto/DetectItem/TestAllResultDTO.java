package org.progetto.dto.DetectItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.progetto.DetectImage.DetectedObject;

import java.util.List;

/**
 * DTO per restituire tutte le detections in un TestResource.
 */
@Getter
@Setter
@NoArgsConstructor
public class TestAllResultDTO {
    public List<DetectedObject> detections;

    public TestAllResultDTO(List<DetectedObject> detections) {
        this.detections = detections;
    }
}

