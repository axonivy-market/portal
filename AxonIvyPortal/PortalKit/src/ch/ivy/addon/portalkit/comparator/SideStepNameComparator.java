package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteSideStep;

public class SideStepNameComparator implements Comparator<RemoteSideStep> {

  @Override
  public int compare(RemoteSideStep firstProcess, RemoteSideStep secondProcess) {
    return firstProcess.getName().compareTo(secondProcess.getName());
  }

}
