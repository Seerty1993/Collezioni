package org.progetto.services;


import jakarta.enterprise.context.ApplicationScoped;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ImageComparatorService {

    static {
        // Carica la libreria OpenCV nativa
        nu.pattern.OpenCV.loadLocally();
    }

    public double compareImages(byte[] localImageBytes, byte[] uploadedImageBytes) {
        Mat img1 = Imgcodecs.imdecode(new MatOfByte(localImageBytes), Imgcodecs.IMREAD_GRAYSCALE);
        Mat img2 = Imgcodecs.imdecode(new MatOfByte(uploadedImageBytes), Imgcodecs.IMREAD_GRAYSCALE);

        if (img1.empty() || img2.empty()) {
            throw new IllegalArgumentException("Una o entrambe le immagini sono vuote o invalide.");
        }

        ORB orb = ORB.create();

        MatOfKeyPoint kp1 = new MatOfKeyPoint(), kp2 = new MatOfKeyPoint();
        Mat desc1 = new Mat(), desc2 = new Mat();

        orb.detectAndCompute(img1, new Mat(), kp1, desc1);
        orb.detectAndCompute(img2, new Mat(), kp2, desc2);

        if (desc1.empty() || desc2.empty()) {
            return 0.0;
        }

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        List<MatOfDMatch> knnMatches = new ArrayList<>();
        matcher.knnMatch(desc1, desc2, knnMatches, 2);

        // Lowe's ratio test
        List<DMatch> goodMatches = new ArrayList<>();
        for (MatOfDMatch matOfDMatch : knnMatches) {
            DMatch[] matches = matOfDMatch.toArray();
            if (matches.length >= 2 && matches[0].distance < 0.75 * matches[1].distance) {
                goodMatches.add(matches[0]);
            }
        }

        // Calcola similarità: rapporto tra good matches e keypoints medi
        int totalKeypoints = (kp1.rows() + kp2.rows()) / 2;
        double similarity = totalKeypoints > 0 ? (double) goodMatches.size() / totalKeypoints : 0.0;

        return similarity; // es. 0.65 = 65% match
    }
}
