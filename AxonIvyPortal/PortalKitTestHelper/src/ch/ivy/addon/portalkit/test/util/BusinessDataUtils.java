package ch.ivy.addon.portalkit.test.util;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.business.data.store.restricted.BusinessDataPersistence;

@SuppressWarnings("restriction")
public final class BusinessDataUtils {
  private BusinessDataUtils(){}
  public static void clearAllBusinessData(){
    DiCore.getGlobalInjector().getInstance(BusinessDataPersistence.class).clearAll();
  }
}