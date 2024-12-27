package com.axonivy.portal.components.ivydata.service.impl;

import static com.axonivy.portal.components.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.List;

import com.axonivy.portal.components.enums.AdditionalProperty;
import com.axonivy.portal.components.ivydata.dto.IvyCaseResultDTO;
import com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria;
import com.axonivy.portal.components.util.PermissionUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseService{

  private CaseService() {}

  public static CaseService newInstance() {
    return new CaseService();
  }

  public IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCases(executeCaseQuery(finalQuery, startIndex, count));
      return result;
    });
  }

  public IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setTotalCases(countCases(finalQuery));
     
      return result;
    });
  }

  private CaseQuery extendQuery(CaseSearchCriteria criteria) {
    CaseQuery clonedQuery = criteria.createQuery();

    if (!criteria.isAdminQuery()) {
      clonedQuery.where().and(queryForCurrentUser(criteria.isTechnicalCase(), criteria.isCaseOwnerEnabled()));
    } 
    
    if (isHiddenTasksCasesExcluded()) {
      clonedQuery.where().and(queryExcludeHiddenCases());
    }
    
    return clonedQuery;
  }

  private CaseQuery queryExcludeHiddenCases() {
    return CaseQuery.businessCases().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull();
  }

  private List<ICase> executeCaseQuery(CaseQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getCaseQueryExecutor().getCount(query);
  }

  private CaseQuery queryForCurrentUser(boolean isTechnicalCase, boolean isCaseOwnerEnabled) {
    CaseQuery caseQuery;
    if (isTechnicalCase) {
      caseQuery = CaseQuery.subCases();
    } else {
      caseQuery = CaseQuery.businessCases();
    }

    if (isCaseOwnerEnabled) {
      caseQuery.where().or().currentUserIsOwner();
    }

    if (PermissionUtils.checkCaseReadAllOwnRoleInvolvedPermission()) {
      caseQuery.where().or().currentUserOrHisRolesAreInvolved();
    } else {
      caseQuery.where().or().currentUserIsInvolved();
    }

    return caseQuery;
  }
}
