package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.util.Pair;

public final class CaseUtils {
  private CaseUtils() {
  }
  
  public static ICase findCase(long caseId) {
    return IvyExecutor.executeAsSystem(() -> Ivy.wf().findCase(caseId));
  }

  /**
   * Sets the "HIDE" property to the given case to hide it in case list of Portal.
   * 
   * @param iCase
   */
  public static void setHidePropertyToHideInPortal(ICase iCase) {
    iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).set(AdditionalProperty.HIDE.toString());
  }

  /**
   * Removes the "HIDE" property to the given case to display it in case list of Portal.
   * 
   * @param iCase
   */
  public static void removeHidePropertyToDisplayInPortal(ICase iCase) {
    iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).set(null);
  }

  public static String getProcessStartUriWithCaseParameters(ICase iCase, String requestPath) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    String urlParameters = "?caseId=" + iCase.getId();
    try {
      return collector.findLinkByFriendlyRequestPath(requestPath) + urlParameters;
    } catch (Exception e) {
      Ivy.log().error(e);
      IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(requestPath);
      return RequestUriFactory.createProcessStartUri(process, new Pair<String, String>("caseId", String.valueOf(iCase.getId()))).toASCIIString();
    }
  }
  
  public static List<INote> findNotes(ICase iCase, boolean excludeSystemNotes) {
    Objects.requireNonNull(iCase, "Case must not be null");
    List<INote> notes = iCase.getNotes();
    if (excludeSystemNotes) {
      notes = notes.stream()
          .filter(n -> !StringUtils.equals(n.getWritterName(), ISecurityConstants.SYSTEM_USER_NAME))
          .collect(Collectors.toList());
    }
    return new ArrayList<>(notes);
  }

  public static CaseQuery createBusinessCaseQuery() {
    return CaseQuery.businessCases();
  }
}