package com.axonivy.portal.smart.statistic.provider;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.smart.statistic.provider.dto.DynamicField;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldProposal;
import com.axonivy.portal.smart.statistic.provider.dto.FieldCatalogueResult;

import com.axonivy.portal.enums.statistic.DynamicFieldType;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import com.axonivy.portal.smart.statistic.provider.dto.FieldTitleProposal;

/**
 * Folds what the agent proposed into the catalogue that was derived from real data.
 *
 * The derived list is the authority on which fields exist and what they hold; the agent may only
 * rename and refine them. So this walks the derived fields, never the proposed ones - anything
 * proposed that was not derived is a field nobody can resolve.
 */
public class FieldCatalogueMapper {

  /** A label, not a sentence. */
  private static final int MAX_TITLE_LENGTH = 60;

  private FieldCatalogueMapper() {}

  public static DynamicFieldCatalogue merge(DynamicFieldCatalogue derived,
      FieldCatalogueResult proposal, List<String> languages, List<String> warnings) {
    if (derived == null || derived.isEmpty()) {
      return derived;
    }
    if (proposal == null || proposal.getFields() == null || proposal.getFields().isEmpty()) {
      return derived;
    }

    Set<String> known = new HashSet<>();
    derived.getFields().forEach(field -> known.add(field.getFieldName()));
    proposal.getFields().stream()
        .map(DynamicFieldProposal::getFieldName)
        .filter(name -> StringUtils.isNotBlank(name) && !known.contains(name))
        .forEach(name -> warnings.add(text("I ignored \"{0}\", because that data source has no such field.", name)));

    for (DynamicField field : derived.getFields()) {
      DynamicFieldProposal proposed = proposal.getFields().stream()
          .filter(candidate -> field.getFieldName().equals(candidate.getFieldName()))
          .findFirst().orElse(null);
      if (proposed == null) {
        continue;
      }
      applyType(field, proposed, warnings);
      applyTitles(field, proposed, languages);
    }
    return derived;
  }

  private static void applyType(DynamicField field, DynamicFieldProposal proposed,
      List<String> warnings) {
    DynamicFieldType declared = DynamicFieldType.find(proposed.getType()).orElse(null);
    if (declared == null) {
      return;
    }
    DynamicFieldType derived = field.getType();
    if (declared == derived) {
      return;
    }
    // The derived type says what the payload really holds; a claim it cannot support is refused.
    if (!DynamicFieldType.isCompatible(declared, derived.getJsonKind())) {
      warnings.add(text("I left \"{0}\" as {1}, because the data does not support {2}.", field.getFieldName(),
          derived.name(), declared.name()));
      return;
    }
    field.setType(declared);
  }

  private static void applyTitles(DynamicField field, DynamicFieldProposal proposed,
      List<String> languages) {
    String fallback = field.getTitle();
    List<DisplayName> titles = new ArrayList<>();
    for (FieldTitleProposal title : nullSafe(proposed.getTitles())) {
      String language = StringUtils.trimToNull(title.getLanguage());
      String value = StringUtils.trimToNull(title.getValue());
      if (language == null || value == null || !languages.contains(language)
          || value.length() > MAX_TITLE_LENGTH) {
        continue;
      }
      titles.add(new DisplayName(Locale.forLanguageTag(language), value));
    }
    if (titles.isEmpty()) {
      return;
    }
    // Any language the agent skipped keeps the derived name rather than falling through to
    // whichever entry happens to be first.
    DisplayNameConvertor.initMultipleLanguages(fallback, titles);
    field.setTitles(titles);
  }

  private static <T> List<T> nullSafe(List<T> values) {
    return values == null ? List.of() : values;
  }

  private static String text(String template, Object... params) {
    return MessageFormat.format(template, Arrays.stream(params).map(String::valueOf).toArray());
  }
}
