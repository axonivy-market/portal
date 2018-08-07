package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.workflow.IProcessStart;

public class ProcessNameComparator implements Comparator<IProcessStart> {

  @Override
  public int compare(IProcessStart firstProcess, IProcessStart secondProcess) {
    return firstProcess.getName().toLowerCase().compareTo(secondProcess.getName().toLowerCase());
  }

}
