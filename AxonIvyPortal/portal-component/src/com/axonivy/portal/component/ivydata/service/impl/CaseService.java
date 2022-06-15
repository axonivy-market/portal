package com.axonivy.portal.component.ivydata.service.impl;

import static com.axonivy.portal.component.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.component.enums.AdditionalProperty;
import com.axonivy.portal.component.ivydata.dto.IvyCaseResultDTO;
import com.axonivy.portal.component.ivydata.exception.PortalIvyDataErrorType;
import com.axonivy.portal.component.ivydata.exception.PortalIvyDataException;
import com.axonivy.portal.component.ivydata.searchcriteria.CaseSearchCriteria;
import com.axonivy.portal.component.ivydata.service.ICaseService;
import com.axonivy.portal.component.util.IvyExecutor;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
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
      try {
        CaseQuery finalQuery = extendQuery(criteria);
        result.setCases(executeCaseQuery(finalQuery, startIndex, count));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting cases", ex);
        result
            .setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_CASE.toString())));
      }
      return result;
    });
  }

  @Override
  public IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        CaseQuery finalQuery = extendQuery(criteria);
        result.setTotalCases(countCases(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in counting cases", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_COUNT_CASE.toString())));
      }
      return result;
    });
  }

  private CaseQuery extendQuery(CaseSearchCriteria criteria) {
    CaseQuery finalQuery = criteria.getFinalCaseQuery();
    @SuppressWarnings("deprecation")
    CaseQuery clonedQuery = CaseQuery.fromJson(finalQuery.asJson()); // clone to keep the final query in
                                                                     // CaseSearchCriteria

    if (criteria.hasApps()) {
      if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
        clonedQuery.where()
            .and(queryForUsers(criteria));
      } else {
        clonedQuery.where().and(queryForApplications(criteria.getApps()));
      }
    }

    if (isHiddenTasksCasesExcluded(criteria.getApps())) {
      clonedQuery.where().and(queryExcludeHiddenCases());
    }

    return clonedQuery;
  }

  private CaseQuery queryForUsers(CaseSearchCriteria criteria) {
    List<String> apps = criteria.getApps();
    String involvedUsername = criteria.getInvolvedUsername();
    CaseQuery caseQuery;
    if (criteria.isTechnicalCase()) {
      caseQuery = CaseQuery.subCases();
    } else {
      caseQuery = CaseQuery.businessCases();
    }

    apps.forEach(app -> {
      caseQuery.where().or().userIsInvolved(involvedUsername, app);
      if (criteria.isCaseOwnerEnabled()) {
        caseQuery.where().or().isOwner("#" + involvedUsername, app);
      }
    });
    return caseQuery;
  }

  private CaseQuery queryForApplications(List<String> apps) {
    CaseQuery caseQuery = CaseQuery.businessCases();
    apps.forEach(app -> {
      IApplication application = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(app);
      if (application != null && application.getActivityState() == ActivityState.ACTIVE) {
        caseQuery.where().or().applicationId().isEqual(application.getId());
      }
    });
    return caseQuery;
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
}
