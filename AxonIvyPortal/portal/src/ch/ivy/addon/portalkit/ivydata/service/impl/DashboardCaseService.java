package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.Collections;
import java.util.List;

import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
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
    try {
      return IvyExecutor.executeAsSystem(() -> {
        var subQuery = CaseQuery.businessCases();
        if (!PermissionUtils.checkReadAllCasesPermission()) {
          subQuery.where().and(queryForCurrentUser(false));
        }

        if (isHiddenTasksCasesExcluded()) {
          subQuery.where().and(queryExcludeHiddenCases());
        }
        var finalQuery = query.where().and(subQuery);
        return executeCaseQuery(finalQuery, startIndex, count);
      });
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  public Long countByCaseQuery(CaseQuery query) {
    return IvyExecutor.executeAsSystem(() -> {
      var subQuery = CaseQuery.businessCases();
      if (!PermissionUtils.checkReadAllCasesPermission()) {
        subQuery.where().and(queryForCurrentUser(false));
      }

      if (isHiddenTasksCasesExcluded()) {
        subQuery.where().and(queryExcludeHiddenCases());
      }
      var finalQuery = query.where().and(subQuery);
      return countCases(finalQuery);
    });
  }
}
