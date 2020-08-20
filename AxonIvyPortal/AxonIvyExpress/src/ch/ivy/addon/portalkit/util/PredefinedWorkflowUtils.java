package ch.ivy.addon.portalkit.util;

import ch.ivy.gawfs.enums.ProcessType;
import ch.ivyteam.ivy.environment.Ivy;

public class PredefinedWorkflowUtils {

  private static final String EXPRESS_CATEGORY_PRE_FIX = "ExpressWorkflow";
  private static final String PATH_REGEX = "\\s{1,}|^\\d{1,}|[^0-9\\^a-zA-Z]";
  private static final String REPLACEMENT = "_";
  private static final String DEFAULT_TASK_CATEGORY = "AdhocExpressTasks";

  public static String generateExpressCaseCategoryPath(ProcessType workflowType, String workflowName) {
    String categoryPath = EXPRESS_CATEGORY_PRE_FIX + convertPathName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/adhoc"));
    if (workflowType != ProcessType.AD_HOC) {
      categoryPath = EXPRESS_CATEGORY_PRE_FIX + "/" + convertPathName(workflowName);
    }

    return categoryPath;
  }

  public static String generateExpressTaskCategoryPath(ProcessType workflowType, String workflowName, String taskSubject) {
    String categoryPath = DEFAULT_TASK_CATEGORY;
    if (workflowType != ProcessType.AD_HOC) {
      categoryPath = convertPathName(workflowName) + "/" + convertPathName(taskSubject);
    }

    return categoryPath;
  }

  public static String convertPathName(String categoryPath) {
    return new String(categoryPath).trim().replaceAll(PATH_REGEX, REPLACEMENT);
  }

}
