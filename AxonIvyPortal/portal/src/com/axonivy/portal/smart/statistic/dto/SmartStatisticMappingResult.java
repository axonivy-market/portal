package com.axonivy.portal.smart.statistic.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.bo.Statistic;

/**
 * Outcome of translating a {@link SmartChartSpec} into a {@link Statistic}.
 *
 * Recoverable problems are clamped and reported through {@link #getWarnings()} while the
 * result stays usable; unrecoverable ones - a group-by field that does not exist, an unknown
 * custom field - make it unusable, because quietly substituting a default would produce a
 * plausible-looking chart that answers a different question than the one asked.
 */
public class SmartStatisticMappingResult implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Statistic statistic;
  private final List<String> warnings;
  private final boolean usable;

  private SmartStatisticMappingResult(Statistic statistic, List<String> warnings, boolean usable) {
    this.statistic = statistic;
    this.warnings = warnings == null ? new ArrayList<>() : warnings;
    this.usable = usable;
  }

  public static SmartStatisticMappingResult usable(Statistic statistic, List<String> warnings) {
    return new SmartStatisticMappingResult(statistic, warnings, true);
  }

  public static SmartStatisticMappingResult rejected(List<String> warnings) {
    return new SmartStatisticMappingResult(null, warnings, false);
  }

  public Statistic getStatistic() {
    return statistic;
  }

  public List<String> getWarnings() {
    return warnings;
  }

  public boolean isUsable() {
    return usable;
  }

  public boolean hasWarnings() {
    return !warnings.isEmpty();
  }
}
