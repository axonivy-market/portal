package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig.isHiddenTasksCasesExcluded;
import static ch.ivyteam.ivy.workflow.CaseState.CREATED;
import static ch.ivyteam.ivy.workflow.CaseState.DESTROYED;
import static ch.ivyteam.ivy.workflow.CaseState.DONE;
import static ch.ivyteam.ivy.workflow.CaseState.RUNNING;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.ItemByCategoryStatistic;

import ch.ivy.addon.portalkit.bo.CaseCategoryStatistic;
import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.query.IPagedResult;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseService{

  protected CaseService() {}

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

  public IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCases(executeCaseQuery(finalQuery));
      return result;
    });
  }

  public IvyCaseResultDTO findGlobalSearchCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      IPagedResult<ICase> iCase = Ivy.wf().getCaseQueryExecutor().getResultsPaged(finalQuery);
      result.setCases(iCase.window(startIndex, count));
      result.setTotalCases(iCase.count());
      return result;
    });
  }

  private List<ICase> executeCaseQuery(CaseQuery query) {
    return Sudo.get(() -> {
      return Ivy.wf().getCaseQueryExecutor().getResults(query);
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

  protected List<ICase> executeCaseQuery(CaseQuery query, Integer startIndex, Integer count) {
    return Ivy.wf().getCaseQueryExecutor().getResults(query, startIndex, count);
  }

  protected long countCases(CaseQuery query) {
    return Ivy.wf().getCaseQueryExecutor().getCount(query);
  }
  
  protected CaseQuery queryForCurrentUser(boolean isTechnicalCase) {
    CaseQuery caseQuery;
    if (isTechnicalCase) {
      caseQuery = CaseQuery.subCases();
    } else {
      caseQuery = CaseQuery.businessCases();
    }

    caseQuery = queryForCurrentUser(caseQuery);

    return caseQuery;
  }

  private CaseQuery queryForCurrentUser(CaseQuery caseQuery) {
    if (GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
      caseQuery.where().or().currentUserIsOwner();
    }

    if (com.axonivy.portal.components.util.PermissionUtils
        .checkCaseReadAllOwnRoleInvolvedPermission()) {
        caseQuery.where().or().currentUserOrHisRolesAreInvolved();
      } else {
        caseQuery.where().or().currentUserIsInvolved();
    }

    return caseQuery;
  }

  public IvyCaseResultDTO findCategoriesByCriteria(CaseCategorySearchCriteria criteria) {
    return Sudo.get(() -> {
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
  
  public IvyCaseResultDTO analyzeCaseStateStatistic(CaseSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      finalQuery.aggregate().countRows().groupBy().state();

      Recordset recordSet = Ivy.wf().getCaseQueryExecutor().getRecordset(finalQuery);
      CaseStateStatistic caseStateStatistic = createCaseStateStatistic(recordSet);
      result.setCaseStateStatistic(caseStateStatistic);
      return result;
    });
  }

  public IvyCaseResultDTO analyzeCaseBusinessStateStatistic(CaseSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      finalQuery.aggregate().countRows().groupBy().businessState();

      Recordset recordSet = Ivy.wf().getCaseQueryExecutor().getRecordset(finalQuery);
      CaseStateStatistic caseStateStatistic = createCaseBusinessStateStatistic(recordSet);
      result.setCaseStateStatistic(caseStateStatistic);
      return result;
    });
  }
  
  private CaseStateStatistic createCaseStateStatistic(Recordset recordSet) {
    CaseStateStatistic caseStateStatistic = new CaseStateStatistic();
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int state = Integer.parseInt(record.getField("STATE").toString());
        long numberOfCases = ((Number)(record.getField("COUNT"))).longValue();
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
  
  private CaseStateStatistic createCaseBusinessStateStatistic(Recordset recordSet) {
    CaseStateStatistic caseStateStatistic = new CaseStateStatistic();
    if (recordSet != null) {
      recordSet.getRecords().forEach(record -> {
        int state = Integer.parseInt(record.getField("BUSINESSSTATE").toString());
        long numberOfCases = ((Number)(record.getField("COUNT"))).longValue();
        if (state == CaseBusinessState.DONE.intValue()) {
          caseStateStatistic.setDone(numberOfCases);
        } else if (state == CaseBusinessState.DESTROYED.intValue()) {
          caseStateStatistic.setFailed(numberOfCases);
        } else if (state == CaseBusinessState.OPEN.intValue()) {
          caseStateStatistic.setOpen(numberOfCases);
        }
      });
    }
    return caseStateStatistic;
  }
  
  public IvyCaseResultDTO analyzeElapsedTimeByCaseCategory(CaseSearchCriteria criteria) {
    return Sudo.get(() -> {
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
    CaseQuery clonedQuery = criteria.createQuery();

    if (!criteria.isAdminQuery()) {
      clonedQuery.where().and(queryForCurrentUser(criteria.isTechnicalCase()));
    }

    if (isHiddenTasksCasesExcluded()) {
      clonedQuery.where().and(queryExcludeHiddenCases());
    }
    return clonedQuery;
  }

  public IvyCaseResultDTO findValuesOfCustomString(CaseCustomFieldSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      List<String> customFields = collectCustomField(criteria);
      result.setCustomFields(customFields);
      return result;
    });
  }

  public IvyCaseResultDTO analyzeCaseCategoryStatistic(CaseSearchCriteria criteria) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      List<ItemByCategoryStatistic> statistics = CategoryUtils.createItemCategoryStatistic(result.getCategoryTree());
      result.setItemByCategoryStatistic(statistics);
      return result;
    });
  }

//  private CaseCategoryStatistic createCaseCategoryStatistic(Recordset recordSet) {
//    CaseCategoryStatistic caseCategoryStatistic = new CaseCategoryStatistic();
//    caseCategoryStatistic.setNumberOfCasesByCategory(new HashMap<>());
//    if (recordSet != null) {
//      recordSet.getRecords().forEach(record -> {
//        long numberOfCases = ((Number)(record.getField("COUNT"))).longValue();
//        caseCategoryStatistic.getNumberOfCasesByCategory().put(record.getField("CATEGORY").toString(), numberOfCases);
//      });
//    }
//    return caseCategoryStatistic;
//  }
  
  public IvyCaseResultDTO analyzeCasesByCategoryStatistic(CaseSearchCriteria criteria, List<String> selectedCategories) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      CaseCategoryStatistic caseCategoryStatistic = createCaseCategoryStatistic(result, selectedCategories);
      result.setCaseCategoryStatistic(caseCategoryStatistic);
      return result;
    });
  }
  
  private CaseCategoryStatistic createCaseCategoryStatistic(IvyCaseResultDTO result,
      List<String> selectedCategories) {
    CaseCategoryStatistic caseCategoryStatistic = new CaseCategoryStatistic();
    caseCategoryStatistic.setNumberOfCasesByCategory(new LinkedHashMap<>());
    if (result.getCategoryTree() != null) {
      getNoCategoryCase(caseCategoryStatistic, result.getCategoryTree());
      if (!selectedCategories.isEmpty()) {
        result.getCategoryTree().getChildren().forEach(category -> {
          loopSmallestNode(caseCategoryStatistic, category, selectedCategories);
        });
      } else {
        result.getCategoryTree().getChildren().forEach(category -> {
          putNode(caseCategoryStatistic, category);
        });
      }
    }
    return caseCategoryStatistic;
  }
  
  /**
   * count no category case because the count node not support count it self. It's always count sub node.
   * @param caseCategoryStatistic
   * @param category
   */
  private void getNoCategoryCase(CaseCategoryStatistic caseCategoryStatistic, CategoryTree category) {
    var nonEmptyChildrenCount = category.getChildren().stream().map(CategoryTree::count).mapToInt(Long::intValue).sum();
    var emptyChildrenCount = category.count() - nonEmptyChildrenCount;
    if(emptyChildrenCount > 0) {
     caseCategoryStatistic.getNumberOfCasesByCategory().put(Ivy.cms().co(CategoryUtils.NO_CATEGORY_CMS), emptyChildrenCount);
    }
  }
  
  /**
   * loop to find the group category or child category
   * @param caseCategoryStatistic
   * @param category
   * @param selectedCategories
   */
  private void loopSmallestNode(CaseCategoryStatistic caseCategoryStatistic, CategoryTree category,
      List<String> selectedCategories) {
    if (selectedCategories.contains(category.getRawPath())) { // category selected from tree
      putNode(caseCategoryStatistic, category);
    } else {
      if (!category.getChildren().isEmpty()) { // category not selected from tree so loop into the child node
        category.getChildren().forEach(categoryChil -> {
          if (selectedCategories.contains(categoryChil.getRawPath())) {
            putNode(caseCategoryStatistic, categoryChil);
          } else {
            loopSmallestNode(caseCategoryStatistic, categoryChil, selectedCategories);
          }
        });
      } else {
        putNode(caseCategoryStatistic, category);
      }
    }
  }
  
  public IvyCaseResultDTO analyzeCasesByCategoryStatisticDrilldown(CaseSearchCriteria criteria, String selectedCategory) {
    return Sudo.get(() -> {
      IvyCaseResultDTO result = new IvyCaseResultDTO();
      CaseQuery finalQuery = extendQuery(criteria);
      result.setCategoryTree(CategoryTree.createFor(finalQuery));
      CaseCategoryStatistic caseCategoryStatistic = createCaseCategoryDrilldownStatistic(result, selectedCategory);
      result.setCaseCategoryStatistic(caseCategoryStatistic);
      return result;
    });
  }
  
  private CaseCategoryStatistic createCaseCategoryDrilldownStatistic(IvyCaseResultDTO result, String selectedCategory) {
    CaseCategoryStatistic caseCategoryStatistic = new CaseCategoryStatistic();
    caseCategoryStatistic.setNumberOfCasesByCategory(new LinkedHashMap<>());
    result.getCategoryTree().getChildren().forEach(category -> {
      loopToSelectedNode(caseCategoryStatistic, category, selectedCategory);
    });

    return caseCategoryStatistic;
  }
  
  /**
   * loop to find exactly the node selected in tree for drill down chart
   * @param caseCategoryStatistic
   * @param category
   * @param selectedCategory
   */
  private void loopToSelectedNode(CaseCategoryStatistic caseCategoryStatistic, CategoryTree category, String selectedCategory) {
    if(!category.getChildren().isEmpty()) {
      if(category.getRawPath().contains(selectedCategory)) {
        category.getChildren().forEach(categoryChil -> {
          putNode(caseCategoryStatistic, categoryChil);
        });
      } else {
        category.getChildren().forEach(categoryChil -> {
          loopToSelectedNode(caseCategoryStatistic, categoryChil, selectedCategory);
        });
      }
    }
  }
  
  
  /**
   * put node into Category Map Data for chart Cases by category
   * The parent node is contains child node and can drill down
   * The child node can not drill down
   * @param caseCategoryStatistic
   * @param category
   */
  private void putNode(CaseCategoryStatistic caseCategoryStatistic, CategoryTree category) {
    StringBuilder categoryPath = new StringBuilder(category.getCategory().getPath());
    if (!category.getChildren().isEmpty()) {
      categoryPath.append(CategoryUtils.PARENT_CATEGORY_DELIMITER); //parent Node
    } else {
      categoryPath.append(CategoryUtils.CHILD_CATEGORY_DELIMITER); // child Node
    }
    categoryPath.append(category.getRawPath());
    caseCategoryStatistic.getNumberOfCasesByCategory().put(categoryPath.toString(), category.count());
  }

  private CaseQuery queryForStates(EnumSet<CaseState> states) {
    CaseQuery caseQuery = CaseQuery.create();
    for (CaseState state : states) {
      caseQuery.where().or().state().isEqual(state);
    }
    return caseQuery;
  }
  
  public ICase findCaseByUUID(String uuid) {
    return Sudo.get(() -> {
      CaseQuery caseQuery = CaseQuery.create().where().uuid().isEqual(uuid);
      if (PermissionUtils.checkReadAllCasesPermission()) {
        EnumSet<CaseState> ADVANCE_STATES = EnumSet.of(CREATED, RUNNING, DONE, DESTROYED);
        caseQuery.where().and(queryForStates(ADVANCE_STATES));
      } else {
        EnumSet<CaseState> STANDARD_STATES = EnumSet.of(CREATED, RUNNING, DONE);
        caseQuery.where().and(queryForStates(STANDARD_STATES)).and(queryForCurrentUser(CaseQuery.create()));
      }
      if (isHiddenTasksCasesExcluded()) {
        caseQuery.where().and(queryExcludeHiddenCases());
      }
      ICase caze = Ivy.wf().getCaseQueryExecutor().getFirstResult(caseQuery);
      if (caze == null) {
        caze = findNonpersistentInvolvedCase(uuid);
      }
      return caze;
    });
  }
  
  public boolean isCaseAccessible(String uuid) {
    return findCaseByUUID(uuid) != null;
  }

  private ICase findNonpersistentInvolvedCase(String uuid) {
    ICase caze = Ivy.wf().findCase(uuid);
    if (caze != null && !caze.isPersistent() && caze.getCreatorUser().equals(Ivy.session().getSessionUser())) {
      return caze;
    }
    return null;
  }

}
