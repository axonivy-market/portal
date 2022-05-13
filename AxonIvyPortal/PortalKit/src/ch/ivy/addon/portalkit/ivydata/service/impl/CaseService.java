package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.ICaseService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IWorkflowManager;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.internal.WorkflowManager;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@SuppressWarnings("restriction")
public class CaseService implements ICaseService {

  private CaseService() {
  }
  
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
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_CASE.toString())));
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

  private List<ICase> executeCaseQuery(CaseQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getGlobalContext().getCaseQueryExecutor().getCount(query);
  }
  
  private CaseQuery queryForUsers(String involvedUsername, List<String> apps, boolean isTechnicalCase) {
    boolean isCaseOwnerEnabled = isCaseOwnerEnabled();
    CaseQuery caseQuery;
    if (isTechnicalCase) {
      caseQuery = CaseQuery.subCases();
    } else {
      caseQuery = CaseQuery.businessCases();
    }

    apps.forEach(app -> {
      caseQuery.where().or().userIsInvolved(involvedUsername, app);
      if (isCaseOwnerEnabled) {
        caseQuery.where().or().isOwner("#" + involvedUsername, app);
      }
    });
    return caseQuery;
  }

  private boolean isCaseOwnerEnabled() {
    return Boolean.parseBoolean(new GlobalSettingService().findGlobalSettingValue(GlobalVariable.ENABLE_CASE_OWNER.toString()));
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
  
  @Override
  public IvyCaseResultDTO findCategoriesByCriteria(CaseCategorySearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        IWorkflowManager manager = DiCore.getGlobalInjector().getInstance(WorkflowManager.class);
        CaseQuery finalQuery = criteria.createQuery(manager.getCaseQueryExecutor());
        
        if (criteria.hasApps()) {
          if (criteria.hasInvolvedUsername()) {
            finalQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps(), false));
          } else {
            finalQuery.where().and(queryForApplications(criteria.getApps()));
          }
        }
        if (isHiddenTasksCasesExcluded(criteria.getApps())) {
          finalQuery.where().and(queryExcludeHiddenCases());
        }
        finalQuery.where().and().category().isNotNull().and().category().isNotEqual("Portal");
        result.setCategoryTree(CategoryTree.createFor(finalQuery));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting case category", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_CASE_CATEGORY.toString())));
      }
      return result;
    });
  }
  
  @Override
  public IvyCaseResultDTO analyzeCaseStateStatistic(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        CaseQuery finalQuery = extendQuery(criteria);
        finalQuery.aggregate().countRows().groupBy().state();

        Recordset recordSet = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getRecordset(finalQuery);
        CaseStateStatistic caseStateStatistic = createCaseStateStatistic(recordSet);
        result.setCaseStateStatistic(caseStateStatistic);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting case state statistic", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_CASE_STATE_STATISTIC.toString())));
      }
      return result;
    });
  }
  
  private CaseStateStatistic createCaseStateStatistic(Recordset recordSet) {
    CaseStateStatistic caseStateStatistic = new CaseStateStatistic();
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int state = Integer.parseInt(record.getField("STATE").toString());
        long numberOfCases = Long.parseLong(record.getField("COUNT").toString());
        if (state == CaseState.DONE.intValue()) {
          caseStateStatistic.setDone(numberOfCases);
        } else if (state == CaseState.CREATED.intValue()) {
          caseStateStatistic.setCreated(numberOfCases);
        } else if (state == CaseState.DESTROYED.intValue()) {
          caseStateStatistic.setFailed(numberOfCases);
        } else if (state == CaseState.RUNNING.intValue()) {
          caseStateStatistic.setRunning(numberOfCases);
        }
      });
    }
    return caseStateStatistic;
  }
  
  @Override
  public IvyCaseResultDTO analyzeElapsedTimeByCaseCategory(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      try {
        CaseQuery finalQuery = extendQuery(criteria);
        finalQuery.where().and().businessRuntime().isNotNull();
        finalQuery.aggregate().avgBusinessRuntime().groupBy().category();

        Recordset recordSet = Ivy.wf().getGlobalContext().getCaseQueryExecutor().getRecordset(finalQuery);
        ElapsedTimeStatistic elapsedTimeStatistic = createCategoryToAverageElapsedTimeMap(recordSet);
        result.setElapsedTimeStatistic(elapsedTimeStatistic);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting case elapsed time statistic", ex);
        result.setErrors(Arrays.asList(new PortalIvyDataException(PortalIvyDataErrorType.FAIL_TO_LOAD_CASE_ELAPSED_TIME_STATISTIC.toString())));
      }
      return result;
    });
  }
  
  private ElapsedTimeStatistic createCategoryToAverageElapsedTimeMap(Recordset recordSet) {
    ElapsedTimeStatistic elapsedTimeStatistic = new ElapsedTimeStatistic();
    Map<String, Long> averageElapsedTimeByCategory = new HashMap<>();
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        String categoryName = record.getField("CATEGORY").toString();
        BigDecimal averageElapsedTime =
            Optional.ofNullable((BigDecimal) record.getField("AVGBUSINESSRUNTIME")).orElse(BigDecimal.ZERO);
        long averageElapsedTimeValue = averageElapsedTime.longValue();
        averageElapsedTimeByCategory.put(categoryName, averageElapsedTimeValue);
      });
    }
    elapsedTimeStatistic.setAverageElapsedTimeByCategory(averageElapsedTimeByCategory);
    return elapsedTimeStatistic;
  }
  
  private List<String> collectCustomField(CaseCustomFieldSearchCriteria criteria) {
    String fieldName = criteria.getFieldName();
    Recordset recordset = CaseQuery
        .businessCases()
        .aggregate()
        .countRows()
        .where().and(queryForApplications(criteria.getApps()))
        .groupBy()
        .customField()
        .stringField(fieldName)
        .executor()
        .recordset();
    
    return recordset
        .getRecords()
        .stream()
        .filter(item -> item.getField(fieldName) != null)
        .map(item -> item.getField(fieldName).toString())
        .filter(item -> StringUtils.containsIgnoreCase(item, criteria.getKeyword()))
        .collect(Collectors.toList());
  }

  private CaseQuery queryExcludeHiddenCases() {
    return CaseQuery.businessCases().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull();
  }
  
  private CaseQuery extendQuery(CaseSearchCriteria criteria) {
    CaseQuery finalQuery = criteria.getFinalCaseQuery();
    @SuppressWarnings("deprecation")
    CaseQuery clonedQuery = CaseQuery.fromJson(finalQuery.asJson()); // clone to keep the final query in CaseSearchCriteria

    if (criteria.hasApps()) {
      if (criteria.hasInvolvedUsername() && !criteria.isAdminQuery()) {
        clonedQuery.where().and(queryForUsers(criteria.getInvolvedUsername(), criteria.getApps(), criteria.isTechnicalCase()));
      } else {
        clonedQuery.where().and(queryForApplications(criteria.getApps()));
      }
    }
    
    if (isHiddenTasksCasesExcluded(criteria.getApps())) {
      clonedQuery.where().and(queryExcludeHiddenCases());
    }
    
    return clonedQuery;
  }

  @Override
  public IvyCaseResultDTO findValuesOfCustomString(CaseCustomFieldSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      List<String> customFields = collectCustomField(criteria);
      result.setCustomFields(customFields);
      return result;
    });
  }
}
