package ch.ivy.addon.portalkit.util;

import ch.ivy.gawfs.enums.ProcessType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.CategoryPath;

public class PredefinedWorkflowUtils {

  private static final String EXPRESS_CATEGORY_PRE_FIX = "ExpressWorkflow";
  private static final String DEFAULT_TASK_CATEGORY = "AdhocExpressTasks";

  public static String generateExpressCaseCategoryPath(ProcessType workflowType, String workflowName) {
    String categoryPath = EXPRESS_CATEGORY_PRE_FIX + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/adhoc");
    if (workflowType != ProcessType.AD_HOC) {
      categoryPath = EXPRESS_CATEGORY_PRE_FIX + "/" + workflowName;
    }

    return convertPathName(categoryPath);
  }

  public static String generateExpressTaskCategoryPath(ProcessType workflowType, String workflowName, String taskSubject) {
    String categoryPath = DEFAULT_TASK_CATEGORY;
    if (workflowType != ProcessType.AD_HOC) {
      categoryPath = workflowName + "/" + taskSubject;
    }

    return convertPathName(categoryPath);
  }

  public static String convertPathName(String categoryPath) {
    return new CategoryPath(categoryPath).getValidRawPath();
  }

}
