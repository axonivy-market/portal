package ch.ivy.addon.portalkit.test.util;

import java.util.List;

import ch.ivyteam.ivy.engine.cleanup.EngineCleanup;

public final class BusinessDataUtils {
  private BusinessDataUtils(){}
  public static void clearAllBusinessData() {
    EngineCleanup.clean(List.of("business-data"));
  }
}