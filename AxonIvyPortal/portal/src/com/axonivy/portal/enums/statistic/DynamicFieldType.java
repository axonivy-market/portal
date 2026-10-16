package com.axonivy.portal.enums.statistic;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;

/**
 * What one field of a data provider is, and therefore what it can be measured by. The numeric
 * flavours differ only in that: totalling a percentage means nothing, so PERCENT has no SUM.
 */
public enum DynamicFieldType {

  TEXT("Text", JsonKind.STRING, "words, a name or a code", Aggregation.COUNT),
  BOOLEAN("Yes/no", JsonKind.BOOLEAN, "true or false", Aggregation.COUNT),
  DATE("Date", JsonKind.DATE, "a point in time", Aggregation.COUNT),

  NUMBER("Number", JsonKind.NUMBER, "any other quantity", Aggregation.COUNT, Aggregation.SUM,
      Aggregation.AVG, Aggregation.MIN, Aggregation.MAX),
  CURRENCY("Money", JsonKind.NUMBER, "money", Aggregation.COUNT, Aggregation.SUM, Aggregation.AVG,
      Aggregation.MIN, Aggregation.MAX),
  DURATION("Duration", JsonKind.NUMBER, "an elapsed time", Aggregation.COUNT, Aggregation.SUM,
      Aggregation.AVG, Aggregation.MIN, Aggregation.MAX),
  PERCENT("Percentage", JsonKind.NUMBER, "a rate or a share, whether it is written as 0.15 or as 15",
      Aggregation.COUNT, Aggregation.AVG, Aggregation.MIN, Aggregation.MAX);

  /** What the raw payload has to hold for a field to legitimately carry this type. */
  public enum JsonKind {
    STRING, NUMBER, DATE, BOOLEAN
  }

  private final String label;
  private final JsonKind jsonKind;
  /** What this type means, in the words the agent is given when it classifies a field. */
  private final String meaning;
  private final Set<Aggregation> measures;

  private DynamicFieldType(String label, JsonKind jsonKind, String meaning,
      Aggregation... measures) {
    this.label = label;
    this.jsonKind = jsonKind;
    this.meaning = meaning;
    this.measures = Collections.unmodifiableSet(EnumSet.copyOf(Arrays.asList(measures)));
  }

  public String getLabel() {
    return label;
  }

  public JsonKind getJsonKind() {
    return jsonKind;
  }

  public String getMeaning() {
    return meaning;
  }

  /** May be a chart axis: anything but a number, which makes as many groups as there are records. */
  public boolean isGroupable() {
    return JsonKind.NUMBER != jsonKind;
  }

  /** May be measured by something other than counting records. */
  public boolean isMeasurable() {
    return JsonKind.NUMBER == jsonKind;
  }

  public Set<Aggregation> getMeasures() {
    return measures;
  }

  public boolean supports(Aggregation aggregation) {
    return aggregation != null && measures.contains(aggregation);
  }

  /** The first legal measure, used when an illegal one has to be clamped. */
  public Aggregation defaultMeasure() {
    return measures.contains(Aggregation.COUNT) ? Aggregation.COUNT : measures.iterator().next();
  }

  public boolean needsDateBucket() {
    return this == DATE;
  }

  /** DATE accepts STRING too: ISO dates arrive as strings and the derivation only promotes the
   * ones its pattern recognises. */
  public static boolean isCompatible(DynamicFieldType declared, JsonKind actual) {
    if (declared == null || actual == null) {
      return false;
    }
    if (DATE == declared) {
      return JsonKind.DATE == actual || JsonKind.STRING == actual;
    }
    return declared.jsonKind == actual;
  }

  /** What a field of this raw kind is, before anything better is known about it. */
  public static DynamicFieldType defaultFor(JsonKind kind) {
    if (kind == null) {
      return TEXT;
    }
    return switch (kind) {
      case NUMBER -> NUMBER;
      case DATE -> DATE;
      case BOOLEAN -> BOOLEAN;
      case STRING -> TEXT;
    };
  }

  public static Optional<DynamicFieldType> find(String name) {
    return Arrays.stream(values())
        .filter(type -> type.name().equalsIgnoreCase(name))
        .findFirst();
  }

  /** The types a field of this raw kind may legitimately be given. */
  public static java.util.List<DynamicFieldType> compatibleWith(JsonKind kind) {
    return Arrays.stream(values()).filter(type -> isCompatible(type, kind)).toList();
  }
}
