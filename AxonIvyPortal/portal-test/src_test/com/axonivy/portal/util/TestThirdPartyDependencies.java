package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestThirdPartyDependencies {

  @Test
  void testJsoup() {
    String html = "<html><head><title>Test Title</title></head><body><p>Hello World</p></body></html>";
    Document doc = Jsoup.parse(html);
    assertThat(doc.title()).isEqualTo("Test Title");
    assertThat(doc.body().text()).isEqualTo("Hello World");

    String unsafeHtml = "<p>Hello <script>alert('xss')</script>World</p>";
    String safeHtml = org.jsoup.Jsoup.clean(unsafeHtml, org.jsoup.safety.Safelist.relaxed());
    assertThat(safeHtml).isEqualTo("<p>Hello World</p>");
  }

  @Test
  void testPdfBox() throws Exception {
    try (PDDocument doc = new PDDocument()) {
      doc.addPage(new org.apache.pdfbox.pdmodel.PDPage());
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      doc.save(out);
      byte[] pdfBytes = out.toByteArray();
      assertThat(pdfBytes).isNotEmpty();
      String header = new String(pdfBytes, 0, Math.min(pdfBytes.length, 10));
      assertThat(header).startsWith("%PDF-");
    }
  }

  @Test
  void testGson() {
    Gson gson = new com.google.gson.GsonBuilder().serializeNulls().create();

    // Test serialization
    Map<String, String> map = new java.util.HashMap<>();
    map.put("key", "value");
    String json = gson.toJson(map);
    assertThat(json).contains("\"key\":\"value\"");

    // Test deserialization
    Map<?, ?> deserialized = gson.fromJson(json, java.util.Map.class);
    assertThat(deserialized.get("key")).isEqualTo("value");
  }
}
