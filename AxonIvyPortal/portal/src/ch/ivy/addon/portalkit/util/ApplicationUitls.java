package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.application.app.IApplicationRepository;

public class ApplicationUitls {
  
  public static final String PORTAL_USER_EXAMPLE_PROJECT = "portal-user-example";
  
  public static boolean doesApplicationExist(String appName) {
    IApplicationRepository appRepo = IApplicationRepository.instance();
    return appRepo.findByName(appName).isPresent();
  }

  public static boolean doesPortalUserExampleExist() {
    return doesApplicationExist(PORTAL_USER_EXAMPLE_PROJECT);
  }

  // public static void listAllApplications() {
  // IApplicationRepository appRepo = IApplicationRepository.instance();
  // List<IApplication> apps = appRepo.all();
  // apps.forEach(app -> {
  // Ivy.log().error("App: " + app.getName());
  // });
  // }
}
