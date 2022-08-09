package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.publicapi.CaseAPI;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public final class CaseUtils {

  private CaseUtils() {}

  public static ICase findCase(long caseId) {
    return IvyExecutor.executeAsSystem(() -> Ivy.wf().findCase(caseId));
  }

  public static List<ICase> findSubCasesByBusinessCaseId(long caseId) {
    return IvyExecutor.executeAsSystem(() -> {
      CaseSearchCriteria criteria = new CaseSearchCriteria();
      criteria.setBusinessCase(false);
      criteria.setTechnicalCase(true);
      criteria.setBusinessCaseId(caseId);
      IvyCaseResultDTO ivyCaseResultDTO = CaseService.newInstance().findCasesByCriteria(criteria);
      return Objects.nonNull(ivyCaseResultDTO) ? ivyCaseResultDTO.getCases() : new ArrayList<>();
    });
  }

  /**
   * Set the "HIDE" property to the given case to hide it in case list of Portal.
   * @deprecated Use {@link CaseAPI#setHidePropertyToHideInPortal(ICase)} instead
   * @param iCase target case
   */
  @Deprecated
  public static void setHidePropertyToHideInPortal(ICase iCase) {
    iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).set(AdditionalProperty.HIDE.toString());
  }

  /**
   * Remove the "HIDE" property to the given case to display it in case list of Portal.
   * @deprecated Use {@link CaseAPI#removeHidePropertyToDisplayInPortal(ICase)} instead
   * @param iCase target case
   */
  @Deprecated
  public static void removeHidePropertyToDisplayInPortal(ICase iCase) {
    iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).set(null);
  }

  @Deprecated(since = "9.3")
  public static String getProcessStartUriWithCaseParameters(ICase iCase, String requestPath) {
    String urlParameters = "?caseId=" + iCase.getId();
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(requestPath) + urlParameters;
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
  
  public static void destroyCase(ICase selectedCase) {
    IvyExecutor.executeAsSystem(() -> {
      selectedCase.destroy();
      return Void.class;
    });
  }

  public static List<CaseState> getValidStates() {
    var states = new ArrayList<>(CaseSearchCriteria.STANDARD_STATES);
    if (PermissionUtils.checkReadAllCasesPermission()) {
      states.addAll(CaseSearchCriteria.ADVANCE_STATES);
    } else {
      states.add(CaseState.DONE);
    }
    return states.stream()
        .sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString()))
        .collect(Collectors.toList());
  }

  public static List<CaseState> filterStateByPermission(List<CaseState> states) {
    if (PermissionUtils.checkReadAllTasksPermission()) {
      return states;
    }
    var validStates = getValidStates();
    return CollectionUtils.emptyIfNull(states).stream()
        .filter(state -> validStates.contains(state))
        .collect(Collectors.toList());
  }
}