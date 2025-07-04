package ch.ivy.addon.portalkit.test.util;

import ch.ivyteam.ivy.business.data.store.restricted.BusinessDataPersistence;

@SuppressWarnings("restriction")
public final class BusinessDataUtils {
  private BusinessDataUtils(){}
  public static void clearAllBusinessData() {
    BusinessDataPersistence.instance().clearAll();
  }
}