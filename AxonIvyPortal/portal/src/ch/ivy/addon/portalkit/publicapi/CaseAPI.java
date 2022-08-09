package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Portal API for {@link ICase}
 *
 */
public final class CaseAPI {
  private CaseAPI() {}

  /**
   * Set the "HIDE" additional property to the given case to hide it in case list of Portal.
   * 
   * @param iCase target case
   */
  public static void setHidePropertyToHideInPortal(ICase iCase) {
    iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).set(AdditionalProperty.HIDE.toString());
  }
  
  /**
   * Remove the "HIDE" additional property to the given case to display it in case list of Portal.
   * 
   * @param iCase target case
   */
  public static void removeHidePropertyToDisplayInPortal(ICase iCase) {
    iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).set(null);
  }
}
