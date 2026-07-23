package com.axonivy.portal.components.converter;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.faces.component.UIComponentBase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class TestFilenameConverter {

  static class StubComponent extends UIComponentBase {
    @Override
    public String getFamily() {
      return "stub";
    }
  }

  private final FilenameConverter converter = new FilenameConverter();

  @ParameterizedTest
  @NullSource
  void getAsString_nullValue_returnsEmpty(Object value) {
    StubComponent component = new StubComponent();
    assertThat(converter.getAsString(null, component, value)).isEmpty();
  }

  @Test
  void getAsString_filenameWithExtension_splitsAtLastDot() {
    StubComponent component = new StubComponent();
    String name = converter.getAsString(null, component, "report.final.pdf");
    assertThat(name).isEqualTo("report.final");
    assertThat(component.getAttributes().get("fileExtension")).isEqualTo(".pdf");
  }

  @Test
  void getAsString_filenameWithoutExtension_returnsWholeNameAndEmptyExtension() {
    StubComponent component = new StubComponent();
    String name = converter.getAsString(null, component, "report");
    assertThat(name).isEqualTo("report");
    assertThat(component.getAttributes().get("fileExtension")).isEqualTo("");
  }

  @Test
  void getAsObject_blankValue_returnsEmpty() {
    StubComponent component = new StubComponent();
    assertThat(converter.getAsObject(null, component, "  ")).isEqualTo("");
  }

  @Test
  void getAsObject_valueWithExtensionAttribute_appendsExtension() {
    StubComponent component = new StubComponent();
    component.getAttributes().put("fileExtension", ".pdf");
    assertThat(converter.getAsObject(null, component, "report")).isEqualTo("report.pdf");
  }
}
