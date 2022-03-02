package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.CaseCategoryStatistic;
import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.ICaseService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseService implements ICaseService {

  protected CaseService() {}

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
  public IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCases(executeCaseQuery(finalQuery));
      return result;
    });
  }

  private List<ICase> executeCaseQuery(CaseQuery query) {
    return IvyExecutor.executeAsSystem(() -> {
      return Ivy.wf().getCaseQueryExecutor().getResults(query);
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

  protected List<ICase> executeCaseQuery(CaseQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  private long countCases(CaseQuery query) {
    return Ivy.wf().getCaseQueryExecutor().getCount(query);
  }
  
  protected CaseQuery queryForCurrentUser(boolean isTechnicalCase) {
    CaseQuery caseQuery;
    if (isTechnicalCase) {
      caseQuery = CaseQuery.subCases();
    } else {
      caseQuery = CaseQuery.businessCases();
    }

    caseQuery.where().or().currentUserIsInvolved();
    if (GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
      caseQuery.where().or().currentUserIsOwner();
    }
    
    return caseQuery;
  }

  @Override
  public IvyCaseResultDTO findCategoriesByCriteria(CaseCategorySearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = criteria.createQuery();
      
      if (!criteria.isAdminQuery()) {
        finalQuery.where().and(queryForCurrentUser(false));
      }
      
      if (isHiddenTasksCasesExcluded()) {
        finalQuery.where().and(queryExcludeHiddenCases());
      }
      finalQuery.where().and().category().isNotNull().and().category().isNotEqual("Portal");
      
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      return result;
    });
  }
  
  @Override
  public IvyCaseResultDTO analyzeCaseStateStatistic(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      finalQuery.aggregate().countRows().groupBy().state();

      Recordset recordSet = Ivy.wf().getCaseQueryExecutor().getRecordset(finalQuery);
      CaseStateStatistic caseStateStatistic = createCaseStateStatistic(recordSet);
      result.setCaseStateStatistic(caseStateStatistic);
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
      CaseQuery finalQuery = extendQuery(criteria);
      finalQuery.where().and().businessRuntime().isNotNull();
      finalQuery.aggregate().avgBusinessRuntime().groupBy().category();

      Recordset recordSet = Ivy.wf().getCaseQueryExecutor().getRecordset(finalQuery);
      ElapsedTimeStatistic elapsedTimeStatistic = createCategoryToAverageElapsedTimeMap(recordSet);
      result.setElapsedTimeStatistic(elapsedTimeStatistic);
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

  protected CaseQuery queryExcludeHiddenCases() {
    return CaseQuery.businessCases().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull();
  }
  
  private CaseQuery extendQuery(CaseSearchCriteria criteria) {
    CaseQuery finalQuery = criteria.getFinalCaseQuery();
    CaseQuery clonedQuery = CaseQuery.fromJson(finalQuery.asJson()); // clone to keep the final query in CaseSearchCriteria

    if (!criteria.isAdminQuery()) {
      clonedQuery.where().and(queryForCurrentUser(criteria.isTechnicalCase()));
    } 
    
    if (isHiddenTasksCasesExcluded()) {
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

  @Override
  public IvyCaseResultDTO analyzeCaseCategoryStatistic(CaseSearchCriteria criteria) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      finalQuery.aggregate().countRows().groupBy().category().orderBy().category();

      Recordset recordSet = Ivy.wf().getCaseQueryExecutor().getRecordset(finalQuery);
      CaseCategoryStatistic caseCategoryStatistic = createCaseCategoryStatistic(recordSet);
      result.setCaseCategoryStatistic(caseCategoryStatistic);
      return result;
    });
  }

  private CaseCategoryStatistic createCaseCategoryStatistic(Recordset recordSet) {
    CaseCategoryStatistic caseCategoryStatistic = new CaseCategoryStatistic();
    caseCategoryStatistic.setNumberOfCasesByCategory(new HashMap<>());
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        long numberOfCases = Long.parseLong(record.getField("COUNT").toString());
        caseCategoryStatistic.getNumberOfCasesByCategory().put(record.getField("CATEGORY").toString(), numberOfCases);
      });
    }
    return caseCategoryStatistic;
  }
}
