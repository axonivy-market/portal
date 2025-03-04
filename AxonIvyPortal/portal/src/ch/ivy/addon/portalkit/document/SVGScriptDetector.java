package ch.ivy.addon.portalkit.document;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SVGScriptDetector implements DocumentDetector {

    @Override
    public boolean isSafe(InputStream inputStream) {
        try {
            // Convert InputStream to String
            String svgContent = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            // Parse SVG content using Jsoup
            Document document = Jsoup.parse(svgContent);

            // Find all <script> elements
            Elements scriptElements = document.select("script");

            // If <script> elements exist, return false (unsafe)
            return scriptElements.isEmpty();

        } catch (Exception e) {
            e.printStackTrace();
            // If there's an error, assume it's unsafe
            return false;
        }
    }
}


