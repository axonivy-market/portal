package com.axonivy.portal.migration.common;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.fasterxml.jackson.databind.JsonNode;

public interface IJsonConverter {

  /**
   * @return the version which is introduced by this converter
   */
  AbstractJsonVersion version();

  void convert(JsonNode jsonNode);
}
