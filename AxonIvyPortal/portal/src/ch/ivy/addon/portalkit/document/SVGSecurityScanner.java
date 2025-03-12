package ch.ivy.addon.portalkit.document;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ch.ivyteam.ivy.environment.Ivy;

public class SVGSecurityScanner implements DocumentDetector {

    @Override
    public boolean isSafe(InputStream inputStream) {
        try {
            // Convert InputStream to String
            String svgContent = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            // Parse SVG content using Jsoup
            Document document = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());
            // Find dangerous elements
            Elements dangerousElements = document.select("script, foreignObject, animate, animateTransform, set");
            // Check for dangerous attributes
            boolean hasDangerousAttributes = document.getAllElements().stream()
                .flatMap(element -> element.attributes().asList().stream())
                .anyMatch(attr -> attr.getKey().startsWith("on"));
            // If dangerous elements or attributes exist, return false (unsafe)
            return dangerousElements.isEmpty() && !hasDangerousAttributes;
        } catch (Exception e) {
          Ivy.log().error("SVG security check failed", e);
          return false;
        }
    }
}
