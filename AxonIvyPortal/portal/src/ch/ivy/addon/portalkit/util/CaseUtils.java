package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public final class CaseUtils {

  private CaseUtils() {}

  public static ICase findCase(long caseId) {
    return Sudo.get(() -> Ivy.wf().findCase(caseId));
  }

  public static List<ICase> findSubCasesByBusinessCaseId(long caseId) {
    return Sudo.get(() -> {
      CaseSearchCriteria criteria = new CaseSearchCriteria();
      criteria.setBusinessCase(false);
      criteria.setTechnicalCase(true);
      criteria.setBusinessCaseId(caseId);
      IvyCaseResultDTO ivyCaseResultDTO = CaseService.newInstance().findCasesByCriteria(criteria);
      return Objects.nonNull(ivyCaseResultDTO) ? ivyCaseResultDTO.getCases() : new ArrayList<>();
    });
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
    Sudo.get(() -> {
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
  
  //Convert case state to friendly case state with multiple languages support
  public static String convertToUserFriendlyCaseState(CaseState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    String url = switch (state) {
      case CREATED -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/CREATED_UPPERCASE";
      case RUNNING -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/INPROGRESS";
      case DESTROYED -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/DESTROYED_UPPERCASE";
      case DONE -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/DONE_UPPERCASE";
      default -> StringUtils.EMPTY;
    };
    return Ivy.cms().co(url);
  }
}
