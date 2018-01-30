package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteProcessStart;

public class ProcessNameComparator implements Comparator<RemoteProcessStart> {

  @Override
  public int compare(RemoteProcessStart firstProcess, RemoteProcessStart secondProcess) {
    return firstProcess.getName().toLowerCase().compareTo(secondProcess.getName().toLowerCase());
  }

}
