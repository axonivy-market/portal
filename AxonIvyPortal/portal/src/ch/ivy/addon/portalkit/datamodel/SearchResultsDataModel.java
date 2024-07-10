package ch.ivy.addon.portalkit.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;
import com.axonivy.portal.enums.GlobalSearchScopeCategory;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class SearchResultsDataModel implements Serializable {

  private static final long serialVersionUID = -472756089365264117L;
  
  protected static final String CASE_WIDGET_COMPONENT_ID = "search-results-tabview:case-results";
  protected static final String TASK_WIDGET_COMPONENT_ID = "search-results-tabview:task-results";
  protected String keyword;
  protected TaskLazyDataModel taskDataModel;
  protected CaseLazyDataModel caseDataModel;
  protected List<SearchScopeTaskField> searchScopeTaskFields;
  protected List<SearchScopeCaseField> searchScopeCaseFields;
  protected List<GlobalSearchScopeCategory> globalSearchScopeCategories;

  private static final String SEARCH_TASK_PREFIX = "task: ";
  private static final String SEARCH_CASE_PREFIX = "case: ";
  private static final String SEARCH_PROCESS_PREFIX = "process: ";

  public SearchResultsDataModel() {
    boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
    boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
    taskDataModel = initTaskDataModel();
    taskDataModel.setAdminQuery(hasReadAllTasksPermisson);
    taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

    caseDataModel = initCaseDataModel();
    caseDataModel.setIsAdminQuery(hasReadAllCasesPermission);

    initSearchScopeTaskFields();
    initSearchScopeCaseFields();
    initGlobalSearchScopeCategories();
  }

  private void initSearchScopeTaskFields() {
    String searchScopeTaskFieldsString = Ivy.var().get(GlobalVariable.SEARCH_SCOPE_BY_TASK_FIELDS.getKey());
    if (StringUtils.isNotBlank(searchScopeTaskFieldsString)) {
      searchScopeTaskFields = new ArrayList<>();
      String[] fieldArray = searchScopeTaskFieldsString.split(",");
      for(String field : fieldArray) {
        SearchScopeTaskField fieldEnum = SearchScopeTaskField.valueOf(field.toUpperCase());
        if (fieldEnum != null) {
          searchScopeTaskFields.add(fieldEnum);
        }
      }
    }
  }

  private void initSearchScopeCaseFields() {
    String searchScopeCaseFieldsString = Ivy.var().get(GlobalVariable.SEARCH_SCOPE_BY_CASE_FIELDS.getKey());
    if (StringUtils.isNotBlank(searchScopeCaseFieldsString)) {
      searchScopeCaseFields = new ArrayList<>();
      String[] fieldArray = searchScopeCaseFieldsString.split(",");
      for(String field : fieldArray) {
        SearchScopeCaseField fieldEnum = SearchScopeCaseField.valueOf(field.toUpperCase());
        if (fieldEnum != null) {
          searchScopeCaseFields.add(fieldEnum);
        }
      }
    }
  }

  private void initGlobalSearchScopeCategories() {
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      globalSearchScopeCategories = new ArrayList<>();
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for(String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null) {
          globalSearchScopeCategories.add(fieldEnum);
        }
      }
    }
  }

  public void search() {
    // Placeholder for customization
  }
  
  protected TaskLazyDataModel initTaskDataModel() {
    return new TaskLazyDataModel(TASK_WIDGET_COMPONENT_ID);
  }

  protected CaseLazyDataModel initCaseDataModel() {
    return new CaseLazyDataModel(CASE_WIDGET_COMPONENT_ID);
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
    analyzeKeyword(keyword.toLowerCase());
    this.taskDataModel.getCriteria().setKeyword(this.keyword);
    this.caseDataModel.getCriteria().setKeyword(this.keyword);

    this.taskDataModel.getCriteria().setGlobalSearch(true);
    this.caseDataModel.getCriteria().setGlobalSearch(true);

    if (CollectionUtils.isNotEmpty(searchScopeTaskFields)) {
      this.taskDataModel.getCriteria().setSearchScopeTaskFields(searchScopeTaskFields);
    }

    if (CollectionUtils.isNotEmpty(searchScopeCaseFields)) {
      this.caseDataModel.getCriteria().setSearchScopeCaseFields(searchScopeCaseFields);
    }

    if (CollectionUtils.isNotEmpty(globalSearchScopeCategories)) {
      this.caseDataModel.getCriteria().setGlobalSearchScope(globalSearchScopeCategories.contains(GlobalSearchScopeCategory.CASES));
      this.taskDataModel.getCriteria().setGlobalSearchScope(globalSearchScopeCategories.contains(GlobalSearchScopeCategory.TASKS));
    }
  }

  public static int getActiveTabIndexByKeyword(String keyword) {
    if (keyword.toLowerCase().startsWith(SEARCH_TASK_PREFIX)) {
      return 1;
    }
    if (keyword.toLowerCase().startsWith(SEARCH_CASE_PREFIX)) {
      return 2;
    }
    return 0;
  }

  private void analyzeKeyword(String keyword) {
    if (keyword.startsWith(SEARCH_PROCESS_PREFIX)) {
      this.keyword = StringUtils.substringAfter(keyword, SEARCH_PROCESS_PREFIX);
    } else if (keyword.startsWith(SEARCH_TASK_PREFIX)) {
      this.keyword = StringUtils.substringAfter(keyword, SEARCH_TASK_PREFIX);
    } else if (keyword.startsWith(SEARCH_CASE_PREFIX)) {
      this.keyword = StringUtils.substringAfter(keyword, SEARCH_CASE_PREFIX);
    }

  }

  public TaskLazyDataModel getTaskDataModel() {
    return taskDataModel;
  }

  public void setTaskDataModel(TaskLazyDataModel taskDataModel) {
    this.taskDataModel = taskDataModel;
  }

  public void setCaseDataModel(CaseLazyDataModel caseDataModel) {
    this.caseDataModel = caseDataModel;
  }

  public CaseLazyDataModel getCaseDataModel() {
    return caseDataModel;
  }
}
