package com.axonivy.portal.components.document;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Validates uploaded images against allowed extensions, file magic bytes,
 * and dangerous script/HTML content.
 */
public class ImageDocumentDetector implements DocumentDetector {

  public static final Set<String> ALLOWED_IMAGE_EXTENSIONS = Set.of("png", "jpg", "jpeg", "svg");

  private static final byte[] PNG_SIGNATURE = new byte[] {
      (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47,
      (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A
  };

  private static final byte[] JPEG_SIGNATURE = new byte[] {
      (byte) 0xFF, (byte) 0xD8, (byte) 0xFF
  };

  private static final Pattern SCRIPT_OR_HTML_PATTERN = Pattern.compile(
      "(?i)(<(script|html|head|body|iframe|object|embed|!doctype)|javascript:)");

  @Override
  public boolean isSafe(InputStream inputStream) {
    if (inputStream == null) {
      return false;
    }
    try {
      byte[] content = inputStream.readAllBytes();
      return isImageSafe(content, null);
    } catch (IOException e) {
      Ivy.log().error("Failed to read image input stream", e);
      return false;
    }
  }

  public static boolean isImageSafe(InputStream inputStream, String extension) {
    if (inputStream == null) {
      return false;
    }
    try {
      byte[] content = inputStream.readAllBytes();
      return isImageSafe(content, extension);
    } catch (IOException e) {
      Ivy.log().error("Failed to read image input stream", e);
      return false;
    }
  }

  public static boolean isImageSafe(byte[] content, String extension) {
    if (content == null || content.length == 0) {
      return false;
    }

    String ext = StringUtils.lowerCase(extension != null ? extension.trim() : null);

    // If extension is supplied, verify it is an allowed image extension
    if (ext != null && !isAllowedImageExtension(ext)) {
      return false;
    }

    if ("svg".equals(ext)) {
      return isSafeSvg(content);
    } else if ("png".equals(ext)) {
      return isSafePng(content);
    } else if ("jpg".equals(ext) || "jpeg".equals(ext)) {
      return isSafeJpeg(content);
    } else if (ext == null) {
      // Auto-detect format from magic bytes if extension not provided
      if (matchesMagicBytes(content, PNG_SIGNATURE)) {
        return isSafePng(content);
      } else if (matchesMagicBytes(content, JPEG_SIGNATURE)) {
        return isSafeJpeg(content);
      } else if (isPotentialSvgContent(content)) {
        return isSafeSvg(content);
      }
      return false;
    }

    return false;
  }

  public static boolean isAllowedImageExtension(String extension) {
    if (StringUtils.isBlank(extension)) {
      return false;
    }
    String cleanExt = FilenameUtils.getExtension(extension);
    if (StringUtils.isBlank(cleanExt)) {
      cleanExt = extension;
    }
    return ALLOWED_IMAGE_EXTENSIONS.contains(cleanExt.toLowerCase(Locale.ROOT).trim());
  }

  private static boolean isSafePng(byte[] content) {
    if (!matchesMagicBytes(content, PNG_SIGNATURE)) {
      return false;
    }
    return !containsScriptOrHtml(content);
  }

  private static boolean isSafeJpeg(byte[] content) {
    if (!matchesMagicBytes(content, JPEG_SIGNATURE)) {
      return false;
    }
    return !containsScriptOrHtml(content);
  }

  private static boolean isSafeSvg(byte[] content) {
    if (!isPotentialSvgContent(content)) {
      return false;
    }
    String svgText = new String(content, StandardCharsets.UTF_8);
    return SVGSecurityScanner.isSafe(svgText);
  }

  private static boolean matchesMagicBytes(byte[] content, byte[] signature) {
    if (content.length < signature.length) {
      return false;
    }
    for (int i = 0; i < signature.length; i++) {
      if (content[i] != signature[i]) {
        return false;
      }
    }
    return true;
  }

  private static boolean isPotentialSvgContent(byte[] content) {
    if (content == null || content.length < 4) {
      return false;
    }
    String snippet = new String(content, 0, Math.min(content.length, 1024), StandardCharsets.UTF_8).trim().toLowerCase(Locale.ROOT);
    return snippet.startsWith("<svg") || snippet.contains("<svg") || snippet.startsWith("<?xml");
  }

  private static boolean containsScriptOrHtml(byte[] content) {
    // Scan ASCII/UTF-8 representation of content for script or HTML injection
    String text = new String(content, StandardCharsets.ISO_8859_1);
    return SCRIPT_OR_HTML_PATTERN.matcher(text).find();
  }
}
