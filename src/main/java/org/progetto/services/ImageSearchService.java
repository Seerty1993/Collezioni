package org.progetto.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;

import nu.pattern.OpenCV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class ImageSearchService {

    static {
        OpenCV.loadLocally();
    }

    private final ORB orb = ORB.create();
    private final DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);


    // Rappresenta un'immagine di catalogo con i suoi descriptors
    public static class CatalogEntry {
        public final String filename;
        public final Mat descriptors;
        public final int keypointCount;

        public CatalogEntry(String filename, Mat descriptors, int keypointCount) {
            this.filename = filename;
            this.descriptors = descriptors;
            this.keypointCount = keypointCount;
        }
    }

    private final List<CatalogEntry> catalog = new ArrayList<>();

    // Carica e indicizza tutte le immagini nella directory
    public void loadCatalog(String dirPath) throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get(dirPath))) {
            paths.filter(p -> p.toString().matches(".*\\.(jpg|png)"))
                    .forEach(p -> {
                        Mat img = Imgcodecs.imread(p.toString(), Imgcodecs.IMREAD_GRAYSCALE);
                        MatOfKeyPoint kp = new MatOfKeyPoint();
                        Mat desc = new Mat();
                        orb.detectAndCompute(img, new Mat(), kp, desc);
                        catalog.add(new CatalogEntry(p.getFileName().toString(), desc, kp.rows()));
                    });
        }
    }

    // Cerca l’immagine più simile tra quelle indicizzate
    public String findBestMatch(byte[] queryBytes) throws IOException {
        Mat query = Imgcodecs.imdecode(new MatOfByte(queryBytes), Imgcodecs.IMREAD_GRAYSCALE);
        MatOfKeyPoint kpQ = new MatOfKeyPoint();
        Mat descQ = new Mat();
        orb.detectAndCompute(query, new Mat(), kpQ, descQ);

        String bestFile = null;
        double bestScore = 0;

        for (CatalogEntry e : catalog) {
            List<MatOfDMatch> knn = new ArrayList<>();
            matcher.knnMatch(descQ, e.descriptors, knn, 2);

            int good = 0;
            for (MatOfDMatch m : knn) {
                DMatch[] dm = m.toArray();
                if (dm.length >= 2 && dm[0].distance < 0.75 * dm[1].distance) {
                    good++;
                }
            }
            double score = e.keypointCount > 0
                    ? (double) good / ((kpQ.rows() + e.keypointCount) / 2)
                    : 0;
            if (score > bestScore) {
                bestScore = score;
                bestFile = e.filename;
            }
        }

        return bestFile + " (" + (bestScore * 100) + "% match)";
    }
}
