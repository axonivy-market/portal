package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public interface ProcessWithCustomField {
  public String getSortIndex();
  public String getPortalProcessInformation();

  /**
   * This method collect the index of process define by Custom Field name portalSortIndex
   * 
   * @param process
   * @return the value of index in process custom field
   */
  default public String getSortIndexInCustomField(IWebStartable process) {
    return process.customFields().value(CustomFields.SORT_INDEX);
  }

  /**
   * This method get the override template for process information defined by Custom Field name portalProcessInformation
   * 
   * @param process
   * @return the value of override template in process custom field
   */
  default public String getPortalProcessInformation(IWebStartable process) {
    return process.customFields().value(CustomFields.PROCESS_INFORMATION);
  }
}
