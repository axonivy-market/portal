package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.casemap.runtime.ISideStepProcess;

public class SideStepNameComparator implements Comparator<ISideStepProcess> {

  @Override
  public int compare(ISideStepProcess firstProcess, ISideStepProcess secondProcess) {
    return firstProcess.getName().compareTo(secondProcess.getName());
  }

}
