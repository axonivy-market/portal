package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.workflow.ICase;

public class CaseNameComparator implements Comparator<ICase> {

  @Override
  public int compare(ICase first, ICase second) {
    return first.getName().compareToIgnoreCase(second.getName());
  }

}
