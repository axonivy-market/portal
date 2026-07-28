package com.axonivy.portal.components.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestProcessType {

  @Test
  void typeOf_externalLink_returnsExternalLink() {
    assertThat(ProcessType.typeOf("ExternalLink")).isEqualTo(ProcessType.EXTERNAL_LINK);
  }

  @Test
  void typeOf_caseMap_isCaseInsensitive() {
    assertThat(ProcessType.typeOf("CASEMAP")).isEqualTo(ProcessType.CASE_MAP);
  }

  @Test
  void typeOf_ivyProcess_returnsIvyProcess() {
    assertThat(ProcessType.typeOf("process-start")).isEqualTo(ProcessType.IVY_PROCESS);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "unknown-type" })
  void typeOf_unknownOrBlankValue_returnsNull(String value) {
    assertThat(ProcessType.typeOf(value)).isNull();
  }
}
