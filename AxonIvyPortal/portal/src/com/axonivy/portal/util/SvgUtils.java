package com.axonivy.portal.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.nio.charset.StandardCharsets;

import com.axonivy.portal.components.document.SVGSecurityScanner;

import ch.ivyteam.ivy.environment.Ivy;

public class SvgUtils {
  static boolean isPotentialSvg(String extension, byte[] data) {
    if (isSvgExtension(extension)) {
      return true;
    }
    return isSvgContent(data);
  }

  static boolean isSvgExtension(String extension) {
    if (extension == null) {
      return false;
    }
    String lower = extension.toLowerCase();
    return "svg".equals(lower) || lower.startsWith("svg+") || "svgz".equals(lower);
  }

  static boolean isSvgContent(byte[] data) {
    if (data == null || data.length < 4) {
      return false;
    }
    String prefix = new String(data, 0, Math.min(data.length, 200), StandardCharsets.UTF_8).trim().toLowerCase();
    return prefix.startsWith("<svg");
  }

  static boolean isUnsafeSvg(byte[] data) {
    try {
      String content = new String(data, StandardCharsets.UTF_8);
      return !SVGSecurityScanner.isSafe(content);
    } catch (Exception e) {
      Ivy.log().warn("Error during SVG safety check: {0}", e.getMessage());
      return true;
    }
  }

  static String normalizeSvgExtension(String ext) {
    if (ext == null) {
      return EMPTY;
    }
    String lower = ext.toLowerCase();
    if (lower.startsWith("svg")) {
        return "svg";
    }
    return ext;
  }
}
