package com.axonivy.portal.service;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.GlobalSearchScopeCategory;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class GlobalSearchService {

  private static GlobalSearchService instance;
  public static GlobalSearchService getInstance() {
    if (instance == null) {
      instance = new GlobalSearchService();
    }
    return GlobalSearchService.instance;
  }

  
  public boolean isShowGlobalSearchByProcesses() {
    boolean isShowFullProcessList = PermissionUtils.checkAccessFullProcessListPermission();
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    boolean isHasSearchScope = false;
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for (String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null && fieldEnum.equals(GlobalSearchScopeCategory.PROCESSES)) {
          isHasSearchScope = true;
          break;
        }
      }
    }
    return isShowFullProcessList && isHasSearchScope;
  }

  public boolean isShowGlobalSearchByCases() {
    boolean isShowFullCaseList = PermissionUtils.checkAccessFullCaseListPermission();
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    boolean isHasSearchScope = false;
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for (String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null && fieldEnum.equals(GlobalSearchScopeCategory.CASES)) {
          isHasSearchScope = true;
          break;
        }
      }
    }
    return isShowFullCaseList && isHasSearchScope;
  }
  
  public boolean isShowGlobalSearchByTasks() {
    boolean isShowFullTaskList = PermissionUtils.checkAccessFullTaskListPermission();
    String globalSearchScopeCategoriesString = Ivy.var().get(GlobalVariable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey());
    boolean isHasSearchScope = false;
    if (StringUtils.isNotBlank(globalSearchScopeCategoriesString)) {
      String[] fieldArray = globalSearchScopeCategoriesString.split(",");
      for (String field : fieldArray) {
        GlobalSearchScopeCategory fieldEnum = GlobalSearchScopeCategory.valueOf(field.toUpperCase());
        if (fieldEnum != null && fieldEnum.equals(GlobalSearchScopeCategory.TASKS)) {
          isHasSearchScope = true;
          break;
        }
      }
    }
    return isShowFullTaskList && isHasSearchScope;
  }
  
}