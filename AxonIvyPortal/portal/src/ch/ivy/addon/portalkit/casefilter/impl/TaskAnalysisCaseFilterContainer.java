package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class TaskAnalysisCaseFilterContainer extends DefaultCaseFilterContainer {

  public TaskAnalysisCaseFilterContainer() {
    super();
    Collections.sort(filters, new CaseFilterComparator());
  }

}
