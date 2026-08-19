package com.axonivy.portal.migration.casedetails.converter;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.migration.common.AbstractJsonConverterFactory;
import com.axonivy.portal.migration.common.IJsonConverter;

public class JsonCaseDetailsConverterFactory extends AbstractJsonConverterFactory {

  private static final List<IJsonConverter> CONVERTERS = new ArrayList<>();

  public static List<IJsonConverter> getConverters(AbstractJsonVersion version) {
    return selectConverters(CONVERTERS, version);
  }

  private static final List<IJsonConverter> LE140 = List
      .of(new com.axonivy.portal.migration.casedetails.converter.v140.CaseDetailsWidgetConverter());

  static {
    CONVERTERS.addAll(LE140);
  }
}
