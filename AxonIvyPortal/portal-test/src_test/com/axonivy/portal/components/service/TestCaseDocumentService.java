package com.axonivy.portal.components.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.ivyteam.ivy.workflow.document.Path;

class TestCaseDocumentService {

  private final CaseDocumentService service = CaseDocumentService.newInstance(null);

  @ParameterizedTest
  @ValueSource(strings = { "bad\\name.pdf", "bad/name.pdf", "bad:name.pdf", "bad*name.pdf", "bad?name.pdf",
      "bad\"name.pdf", "bad<name.pdf", "bad>name.pdf", "bad|name.pdf" })
  void containsForbiddenChars_forbiddenChar_returnsTrue(String filename) {
    assertThat(service.containsForbiddenChars(filename)).isTrue();
  }

  @Test
  void containsForbiddenChars_validFilename_returnsFalse() {
    assertThat(service.containsForbiddenChars("valid-name_v1.pdf")).isFalse();
  }

  @Test
  void isDocumentTypeValid_blankAllowedTypes_returnsTrue() {
    assertThat(CaseDocumentService.isDocumentTypeValid("file.pdf", "")).isTrue();
  }

  @Test
  void isDocumentTypeValid_allowedType_returnsTrue() {
    assertThat(CaseDocumentService.isDocumentTypeValid("file.pdf", "pdf, docx")).isTrue();
  }

  @Test
  void isDocumentTypeValid_notAllowedType_returnsFalse() {
    assertThat(CaseDocumentService.isDocumentTypeValid("file.exe", "pdf, docx")).isFalse();
  }

  @Test
  void isDocumentTypeValid_caseInsensitiveExtension_returnsTrue() {
    assertThat(CaseDocumentService.isDocumentTypeValid("file.PDF", "pdf")).isTrue();
  }

  @Test
  void getPathAfterRename_returnsParentPathWithNewName() {
    Path oldPath = new Path("folder/sub/old.pdf");
    Path result = CaseDocumentService.getPathAfterRename(oldPath, "new.pdf");
    assertThat(result.toString()).isEqualTo("folder/sub/new.pdf");
  }

  @Test
  void getPathAfterRename_topLevelFile_returnsNewNameOnly() {
    Path oldPath = new Path("old.pdf");
    Path result = CaseDocumentService.getPathAfterRename(oldPath, "new.pdf");
    assertThat(result.toString()).isEqualTo("new.pdf");
  }
}
