package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.note.Note;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public final class CaseUtils {
  private static String pinCaseProperty = UserProperty.PORTAL_PINED_CASES;

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

  public static List<Note> findNotes(ICase iCase, boolean excludeSystemNotes) {
    Objects.requireNonNull(iCase, "Case must not be null");
    List<Note> notes = iCase.notes().all();
    if (excludeSystemNotes) {
      notes = notes.stream()
          .filter(n -> !StringUtils.equals(n.authorName(), ISecurityConstants.SYSTEM_USER_NAME))
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

  /**
   * Get valid business case states
   * 
   * @return valid business case states
   */
  public static List<CaseBusinessState> getValidStates() {
    var states = new ArrayList<>(CaseSearchCriteria.STANDARD_BUSINESS_STATES);
    if (PermissionUtils.checkReadAllCasesPermission()) {
      states.addAll(CaseSearchCriteria.ADVANCE_BUSINESS_STATES);
    } else {
      states.add(CaseBusinessState.DONE);
    }
    return states.stream()
        .sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString()))
        .collect(Collectors.toList());
  }

  /**
   * Get valid old states
   * 
   * @return valid old states
   */
  @Deprecated(since = "11.1")
  public static List<CaseState> getOldValidStates() {
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

  public static List<CaseBusinessState> filterStateByPermission(List<CaseBusinessState> states) {
    var validStates = getValidStates();
    return CollectionUtils.emptyIfNull(states).stream()
        .filter(state -> validStates.contains(state))
        .collect(Collectors.toList());
  }
  
  //Convert case state to friendly case state with multiple languages support
  public static String convertToUserFriendlyCaseState(CaseBusinessState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    String url = switch(state) {
      case OPEN -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/OPEN_UPPERCASE";
      case DESTROYED -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/DESTROYED_UPPERCASE";
      case DONE -> "/ch.ivy.addon.portalkit.ui.jsf/caseState/DONE_UPPERCASE";
      default -> StringUtils.EMPTY;
    };
    return Ivy.cms().co(url);
  }
  
  public static boolean isCaseOwnerUser(ICase caze) {
    return caze != null && caze.owners() != null && caze
        .owners()
        .all()
        .stream()
        .anyMatch(item -> item.member().isMember(Ivy.session(), true));
  }

  public static boolean isPinnedCase(ICase caze) {
    return caze != null && getPinnedCaseUuids().contains(caze.uuid());
  }

  public static Set<String> getPinnedCaseUuids() {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser == null) {
      return new HashSet<>();
    }

    String pinnedCasesStr = currentUser.getProperty(pinCaseProperty);
    return StringUtils.isBlank(pinnedCasesStr) ? new HashSet<>()
        : Arrays.stream(pinnedCasesStr.split(",")).map(String::trim).filter(StringUtils::isNotEmpty)
            .collect(Collectors.toSet());
  }

  public static void markCaseAsPinned(ICase caze) {
    if (caze == null) {
      return;
    }

    Set<String> pinnedCaseUuids = getPinnedCaseUuids();
    boolean ispinned = pinnedCaseUuids.contains(caze.uuid());

    if (ispinned) {
      pinnedCaseUuids.remove(caze.uuid());
    } else {
      pinnedCaseUuids.add(caze.uuid());
    }

    savePinnedCaseUuids(pinnedCaseUuids);
  }

  public static void savePinnedCaseUuids(Set<String> pinnedCaseUuids) {
    String updatedPinnedCases = String.join(",", pinnedCaseUuids);
    Ivy.session().getSessionUser().setProperty(pinCaseProperty, updatedPinnedCases);
  }

  public static void removeAllPinnedCase() {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser != null) {
      currentUser.setProperty(pinCaseProperty, StringUtils.EMPTY);
    }
  }
}
