package com.axonivy.portal.smart.statistic.prompt;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.statistic.DynamicFieldType;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;

import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;

/**
 * What the agent is told when it is asked to name a provider's fields. Separate from the chart
 * prompt on purpose: this call needs none of the chart vocabulary.
 */
public final class FieldNamingPrompt {

  private FieldNamingPrompt() {}

  /**
   * The system message for the field-naming call. Deliberately its own, and short: this call needs
   * none of the chart vocabulary, and sharing the chart prompt invites a chart back instead.
   */
  private static final PromptFormatter SYSTEM_MESSAGE = PromptFormatter.from("""
      You name and classify the fields of one business dataset, so that people can chart it.

      You are given the field paths that were read out of the real data, with their raw kinds,
      a few example values, and what the dataset is for. Work only from that.

      ## Rules

      - Return one entry for EVERY field you are given, using its `fieldName` exactly as written.
      - Never invent a field, and never leave one out.
      - `titles` is one short business label per language listed below, and they are translations
        of each other. Two or three words, as a form field would be labelled: "Invoiced amount",
        not "The amount that was invoiced" and not `totalAmountNet`. No units, no punctuation, no
        field paths.

      Do not describe the data, do not build a chart, and do not comment on data quality.

      ## Field types

      `type` is one of:

      {{fieldTypes}}
      Use the example values and the dataset description to tell them apart - a field called
      `rate` holding 0.05 is a {{percent}}, one holding 45 might be a {{number}}.

      {{typeConstraints}}
      ## Languages

      Give a title for each of these language tags: {{languages}}.
      """);

  public static String systemMessage(List<String> languages) {
    return SYSTEM_MESSAGE.apply(
        "fieldTypes", DataDescriber.fieldTypes(),
        // Named through the constants rather than spelled out, so dropping one is a compile error
        // here instead of a sentence that quietly describes a type that no longer exists.
        "percent", DynamicFieldType.PERCENT,
        "number", DynamicFieldType.NUMBER,
        "typeConstraints", DataDescriber.fieldTypeConstraints(),
        "languages", String.join(", ", languages));
  }

  private static final PromptFormatter QUERY = PromptFormatter.from("""
      ## The dataset

      `{{provider}}`{{description}}

      {{returns}}## Its fields

      {{fields}}
      {{records}}Name and classify every field listed above.
      """);
  private static final PromptFormatter DESCRIPTION = PromptFormatter.from(" - {{text}}");
  private static final PromptFormatter RETURNS = PromptFormatter.from("""
      Returns: {{text}}

      """);
  private static final PromptFormatter RECORDS = PromptFormatter.from("""
      ## A few records

      {{rows}}

      """);

  /** What the naming call is given: the dataset's purpose, its real fields, and a few records. */
  public static String query(SubProcessCallStartEvent provider,
      DynamicFieldCatalogue derived, String sampleRows) {
    var description = provider.description();
    String returns = description.out().stream()
        .filter(param -> StringUtils.isNotBlank(param.description()))
        .map(param -> RETURNS.apply("text", oneLine(param.description())))
        .collect(Collectors.joining());

    return QUERY.apply(
        "provider", description.name(),
        "description", StringUtils.isBlank(description.description()) ? ""
            : DESCRIPTION.apply("text", oneLine(description.description())),
        "returns", returns,
        "fields", DataDescriber.fields(derived),
        "records", StringUtils.isBlank(sampleRows) ? ""
            : RECORDS.apply("rows", sampleRows));
  }

  /** Process descriptions are written across several lines; the prompt wants one. */
  private static String oneLine(String text) {
    return text.replaceAll("\\s+", " ").trim();
  }
}
