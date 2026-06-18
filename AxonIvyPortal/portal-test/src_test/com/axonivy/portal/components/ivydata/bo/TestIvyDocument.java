package com.axonivy.portal.components.ivydata.bo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestIvyDocument {

  @Test
  void builder_setsAllFields() {
    var doc = IvyDocument.builder()
        .uuid("doc-uuid-123")
        .name("contract.pdf")
        .path("/cases/42/contract.pdf")
        .relativePath("contract.pdf")
        .size(2048L)
        .contentType("application/pdf")
        .create();

    assertThat(doc.getUuid()).isEqualTo("doc-uuid-123");
    assertThat(doc.getName()).isEqualTo("contract.pdf");
    assertThat(doc.getPath()).isEqualTo("/cases/42/contract.pdf");
    assertThat(doc.getRelativePath()).isEqualTo("contract.pdf");
    assertThat(doc.getSize()).isEqualTo(2048L);
    assertThat(doc.getContentType()).isEqualTo("application/pdf");
  }

  @Test
  void builder_defaultsToNullFields_whenNotSet() {
    var doc = IvyDocument.builder().name("empty.pdf").create();

    assertThat(doc.getUuid()).isNull();
    assertThat(doc.getSize()).isNull();
    assertThat(doc.getContentType()).isNull();
  }

  @Test
  void getUserFriendlySize_returnsOneKb_whenSizeLessThan1024() {
    var doc = IvyDocument.builder().size(512L).create();

    assertThat(doc.getUserFriendlySize()).isEqualTo("1KB");
  }

  @Test
  void getUserFriendlySize_returnsDividedByKb_whenSizeAtLeast1024() {
    var doc = IvyDocument.builder().size(3072L).create();

    assertThat(doc.getUserFriendlySize()).isEqualTo("3KB");
  }
}
