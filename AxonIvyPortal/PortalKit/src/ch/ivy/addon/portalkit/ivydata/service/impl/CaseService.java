package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.List;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.ICaseService;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseService implements ICaseService {

  private CaseService() {
  }
  
  public static CaseService newInstance() {
    return new CaseService();
  }
  
  @Override
  public IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count) throws Exception { // NOSONAR
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        CaseQuery finalQuery = criteria.getFinalCaseQuery();
        if (criteria.hasApps()) {
          if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
            finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
          } else {
            finalQuery.where().and(queryForApplications(criteria.getApps()));
          }
        }
//        finalQuery.where().and(queryExcludeHiddenCases());
        result.setCases(executeCaseQuery(finalQuery, startIndex, count));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting cases", ex);
//        result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString())));
      }
      return result;
    });
  }
  
  @Override
  public IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria) throws Exception { // NOSONAR
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        CaseQuery finalQuery = criteria.getFinalCaseQuery();
        if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
          finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
        } else {
          finalQuery.where().and(queryForApplications(criteria.getApps()));
        }
//        finalQuery.where().and(queryExcludeHiddenCases());
        result.setTotalCases(countCases(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in counting cases", ex);
//        result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString())));
      }
      return result;
    });
  }
  
  private List<ICase> executeCaseQuery(CaseQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getCount(query);
  }
  
  private static CaseQuery queryForUsers(String involvedUsername, List<String> apps) {
    CaseQuery caseQuery = CaseQuery.create();
    apps.forEach(app -> caseQuery.where().or().userIsInvolved(involvedUsername, app));
    return caseQuery;
  }
  
  private CaseQuery queryForApplications(List<String> apps) {
    CaseQuery caseQuery = CaseQuery.create();
    apps.forEach(app -> {
      IApplication application = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(app);
      if (application != null && application.getActivityState() == ActivityState.ACTIVE) {
        caseQuery.where().or().applicationId().isEqual(application.getId());
      }
    });
    return caseQuery;
  }
  
  private CaseQuery queryExcludeHiddenCases() {
    return CaseQuery.create().where().additionalProperty(AdditionalProperty.HIDE.toString()).isNull();
  }

  @Override
  public IvyCaseResultDTO findCategoriesByCriteria(CaseCategorySearchCriteria criteria) throws Exception { // NOSONAR
    return getServer().getSecurityManager().executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        CaseQuery finalQuery = criteria.createQuery();
        
        if (criteria.hasApps()) {
          if (criteria.hasInvolvedUsername()) {
            finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps()));
          } else {
            finalQuery.where().and(queryForApplications(criteria.getApps()));
          }
        }
//        finalQuery.where().and(queryExcludeHiddenCases());
        finalQuery.where().and().category().isNotNull().and().category().isNotEqual("Portal");
        result.setCategoryTree(CategoryTree.createFor(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting case category", ex);
//        result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString())));
      }
      return result;
    });
  }
}
