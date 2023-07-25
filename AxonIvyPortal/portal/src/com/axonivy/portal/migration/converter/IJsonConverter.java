package com.axonivy.portal.migration.converter;

import com.axonivy.portal.bo.JsonVersion;
import com.fasterxml.jackson.databind.JsonNode;

public interface IJsonConverter {

  /**
   * @return the version which is introduced by this converter
   */
  JsonVersion version();

  void convert(JsonNode jsonNode);
}
