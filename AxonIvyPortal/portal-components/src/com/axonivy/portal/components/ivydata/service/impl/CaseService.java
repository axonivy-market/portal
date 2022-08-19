package com.axonivy.portal.components.ivydata.service.impl;

import static com.axonivy.portal.components.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.List;

import com.axonivy.portal.components.enums.AdditionalProperty;
import com.axonivy.portal.components.ivydata.dto.IvyCaseResultDTO;
import com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria;
import com.axonivy.portal.components.ivydata.service.ICaseService;
import com.axonivy.portal.components.util.IvyExecutor;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseService implements ICaseService {

  private CaseService() {}

  public static CaseService newInstance() {
    return new CaseService();
  }

  @Override
  public IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCases(executeCaseQuery(finalQuery, startIndex, count));
      return result;
    });
  }

  @Override
  public IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setTotalCases(countCases(finalQuery));
     
      return result;
    });
  }

  private CaseQuery extendQuery(CaseSearchCriteria criteria) {
    CaseQuery finalQuery = criteria.getFinalCaseQuery();
    CaseQuery clonedQuery = CaseQuery.fromJson(finalQuery.asJson()); // clone to keep the final query in CaseSearchCriteria

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
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getCount(query);
  }

  private CaseQuery queryForCurrentUser(boolean isTechnicalCase, boolean isCaseOwnerEnabled) {
    CaseQuery caseQuery;
    if (isTechnicalCase) {
      caseQuery = CaseQuery.subCases();
    } else {
      caseQuery = CaseQuery.businessCases();
    }

    caseQuery.where().or().currentUserIsInvolved();
    if (isCaseOwnerEnabled) {
      caseQuery.where().or().currentUserIsOwner();
    }
    
    return caseQuery;
  }
}
