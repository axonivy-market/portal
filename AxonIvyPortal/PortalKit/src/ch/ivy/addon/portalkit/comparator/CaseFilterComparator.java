package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;

public class CaseFilterComparator implements Comparator<CaseFilter> {

  @Override
  public int compare(CaseFilter first, CaseFilter second) {
    return first.label().compareTo(second.label());
  }

}
