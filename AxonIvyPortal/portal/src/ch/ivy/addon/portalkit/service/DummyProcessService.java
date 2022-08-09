package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.constant.DummyProcess;
import ch.ivy.addon.portalkit.configuration.UserProcess;

public class DummyProcessService {

  public static boolean enableUserFavorites() {
    return true;
  }
  
  public static boolean displayShowAllProcessesLink() {
    return true;
  }
  
  public static List<UserProcess> dummyFavorites() {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(DummyProcess.FAVORITE_NAME);
    userProcess.setIcon(DummyProcess.FAVORITE_ICON);
    return Arrays.asList(userProcess);
  }
  
  public static List<UserProcess> dummyApplicationProcesses() {
    UserProcess appProcess1 = new UserProcess();
    appProcess1.setProcessName(DummyProcess.APP_PROCESS_NAME_1);
    appProcess1.setIcon(DummyProcess.APP_PROCESS_ICON_1);
    UserProcess appProcess2 = new UserProcess();
    appProcess2.setProcessName(DummyProcess.APP_PROCESS_NAME_2);
    appProcess2.setIcon(DummyProcess.APP_PROCESS_ICON_2);
    return Arrays.asList(appProcess1, appProcess2);
  }
}
