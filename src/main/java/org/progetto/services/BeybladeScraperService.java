package org.progetto.services;

import io.quarkus.scheduler.Scheduled;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Scraper per estrarre la lista dei Beyblade X products dal wiki Fandom.
 * Estrae Product Code, Name e Release Date e salva in JSON.
 */
@ApplicationScoped
@Getter
@Slf4j
public class BeybladeScraperService {
    public static final String TARGET_URL = "https://beyblade.fandom.com/wiki/List_of_Beyblade_X_products_(Takara_Tomy)";
    public static final Path OUTPUT_FILE = Paths.get("beyblade_x_products.json");
    private static final Pattern KEYWORD_PATTERN = Pattern.compile(
            "(?i).*?\\b(?:Booster|Starter)\\s+([^()]+?)(?:\\s*\\(.*\\))?\\s*$"
    );
    public Path FilePath = OUTPUT_FILE.toAbsolutePath();
    //     @Scheduled(cron = "0 0 0 * * MON") // esegue ogni lunedì a mezzanotte
//    public void scheduledScrape() {
//        fetchAndSave();
//    }

    /**
     * Estrae e normalizza la porzione di stringa dopo "Booster" o "Starter":
     * - Rimuove la parte tra parentesi
     * - Sostituisce tutti gli spazi con '_'
     *
     * @param input la stringa grezza da normalizzare
     * @return la parte normalizzata (o null se non c'è match)
     */
    public static String normalize(String input) {
        if (input == null) {
            return null;
        }
        Matcher m = KEYWORD_PATTERN.matcher(input);
        if (!m.matches()) {
            return null;
        }
        // Gruppo 1 = es. "CobaltDrake 4-60F"
        String raw = m.group(1).trim();
        // Sostituisci spazi multipli con underscore
        return raw.replaceAll("\\s+", "_");
    }

    public void fetchAndSave() {

        try {
            Document doc = Jsoup.connect(TARGET_URL)
                    .userAgent("Mozilla/5.0 (compatible; QuarkusScraper/1.0)")
                    .timeout(10_000)
                    .get();

            // Seleziona tutte le tabelle con classe "wikitable sortable"
            Elements tables = doc.select("table.wikitable.sortable");
            if (tables.isEmpty()) {
                System.err.println("Nessuna tabella trovata su pagina: " + TARGET_URL);
                return;
            }

            Map<String, Map<String, String>> output = new LinkedHashMap<>();

            // Itera su ciascuna tabella trovata
            for (Element table : tables) {
                Elements rows = table.select("tr");
                // Salta l'intestazione (indice 0)
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if (cols.size() >= 3) {
                        String code = cols.get(0).text().trim();
                        String name = cols.get(1).text().trim();
                        String date = cols.get(2).text().trim();

                        Map<String, String> spin = fetchExtraInfo(normalize(name));
                        if (spin == null) {
                            continue; // Salta se uno dei campi manca
                        }
                        Map<String, String> entry = new LinkedHashMap<>();
                        entry.put("Name", name);
                        entry.put("Release Date", date);
                        entry.putAll(spin);
                        output.put(code, entry);
                        log.info("SONO QUA" + name + spin);
                    }


                }
            }

            // Serializza e salva su file JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(OUTPUT_FILE.toFile(), output);
            Path FilePath = OUTPUT_FILE.toAbsolutePath();

            System.out.println("Scraping completato: dati salvati in " + FilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> fetchExtraInfo(String code) {
        Map<String, String> extraFields = new LinkedHashMap<>();

        try {
            String url = "https://beyblade.fandom.com/" + URLEncoder.encode(code, StandardCharsets.UTF_8);
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (compatible; QuarkusScraper/1.0)")
                    .timeout(10_000)
                    .get();

            Elements rows = doc.select(".portable-infobox .pi-item");

            Map<String, String> fieldsToExtract = new LinkedHashMap<>();
            fieldsToExtract.put("Spin Direction", null);
            fieldsToExtract.put("Blade", null);
            fieldsToExtract.put("Ratchet", null);
            fieldsToExtract.put("Bit", null);
            for (Element row : rows) {
                Element label = row.selectFirst(".pi-data-label");
                Element value = row.selectFirst(".pi-data-value");

                if (label != null && value != null) {
                    String labelText = label.text().trim();
                    for (String key : fieldsToExtract.keySet()) {
                        if (labelText.equalsIgnoreCase(key)) {
                            fieldsToExtract.put(key, value.text().trim());
                        }
                    }
                }
            }

            // Verifica se tutti i campi sono stati trovati
            if (fieldsToExtract.values().stream().anyMatch(v -> v == null)) {
                return null; // Campo mancante → scarta l'oggetto
            }

            // Ritorna i campi trovati
            return fieldsToExtract;
        } catch (Exception e) {
            System.err.println("Errore nel recuperare info per " + code + ": " + e.getMessage());
            return null;
        }
    }
}

//public void InsertJsonBeyblade() {
//
//}

