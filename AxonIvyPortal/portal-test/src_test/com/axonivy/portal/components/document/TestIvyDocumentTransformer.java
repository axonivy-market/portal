package com.axonivy.portal.components.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Only the empty-list fast path is covered here: transforming an actual
 * document needs a real {@code IDocument} instance, which needs Mockito
 * (not used in this project) or a real case/task document, which needs
 * {@code @IvyProcessTest} (not usable yet in this environment).
 */
class TestIvyDocumentTransformer {

  private final IvyDocumentTransformer transformer = new IvyDocumentTransformer();

  @Test
  void transformList_emptyList_returnsEmptyList() {
    assertThat(transformer.transform(List.of())).isEmpty();
  }
}
