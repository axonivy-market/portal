package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.List;

import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class DashboardCaseService extends CaseService {

  private static DashboardCaseService instance;

  public static DashboardCaseService getInstance() {
    if (instance == null) {
      instance = new DashboardCaseService();
    }
    return instance;
  }

  public List<ICase> findByCaseQuery(CaseQuery query, int startIndex, int count) {
    return Sudo.get(() -> {
      var subQuery = CaseQuery.businessCases();
      boolean hasSubQueryChanged = false;
      if (!PermissionUtils.checkReadAllCasesPermission()) {
        subQuery.where().and(queryForCurrentUser(false));
        hasSubQueryChanged = true;
      }
  
      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenCases());
        hasSubQueryChanged = true;
      }
      var finalQuery =  hasSubQueryChanged ? query.where().and(subQuery) : query;
      return executeCaseQuery(finalQuery, startIndex, count);
    });
  }

  public Long countByCaseQuery(CaseQuery query) {
    return Sudo.get(() -> {
      var subQuery = CaseQuery.businessCases();
      boolean hasSubQueryChanged = false;
      if (!PermissionUtils.checkReadAllCasesPermission()) {
        subQuery.where().and(queryForCurrentUser(false));
        hasSubQueryChanged = true;
      }
  
      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenCases());
        hasSubQueryChanged = true;
      }
      var finalQuery = hasSubQueryChanged ? query.where().and(subQuery) : query;
      return countCases(finalQuery);
    });
  }
}
