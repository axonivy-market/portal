package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;

public class SideStepNameComparator implements Comparator<IStartableSideStep> {

  @Override
  public int compare(IStartableSideStep firstProcess, IStartableSideStep secondProcess) {
    return firstProcess.getName().compareTo(secondProcess.getName());
  }

}
