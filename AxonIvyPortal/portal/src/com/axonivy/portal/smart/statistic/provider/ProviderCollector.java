package com.axonivy.portal.smart.statistic.provider;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;

/**
 * Finds the callable subprocesses that offer a data set to the statistic feature, the same way
 * smart-workflow's {@code IvyToolsProcesses} finds its tools.
 *
 * A provider is a CallSubStart tagged {@value #TAG} whose result carries the data. Its process
 * description and its result parameter descriptions are what the agent reads, so they are part
 * of the contract - see {@code accounting/process/accounting/StatisticProvider}.
 *
 * The scope is APPLICATION rather than smart-workflow's PROJECT_AND_ALL_REQUIRED: a providing
 * project supplies data to Portal without either side depending on the other, so a
 * required-projects search would never reach it.
 */
public class ProviderCollector {

  public static final String TAG = "portal-statistic";

  private SearchScope scope = SearchScope.APPLICATION;

  public ProviderCollector scope(SearchScope scope) {
    this.scope = scope;
    return this;
  }

  public List<SubProcessCallStartEvent> providerStarts() {
    return SubProcessCallStartEvent.find(SubProcessSearchFilter.create()
        .setSearchScope(scope)
        .taggedAs(TAG)
        .toFilter());
  }

  /** @param name the start's method name, as {@code provideAccountsReceivable} */
  public Optional<SubProcessCallStartEvent> findByName(String name) {
    if (StringUtils.isBlank(name)) {
      return Optional.empty();
    }
    return providerStarts().stream()
        .filter(start -> name.equals(start.description().name()))
        .findFirst();
  }
}
