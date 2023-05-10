package portalmigration.version112.util;

import ch.ivyteam.ivy.cm.exec.LocalizedTextResolverFactory;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.category.CategoryPath;

public class CategoryUtils {

  private static final String ALL = "All";
  public static final String NO_CATEGORY = "[No Category]";
  public static final String NO_CATEGORY_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/noCategory";
  public static final String CATEGORY_SEPARATOR = ", ";
  public static final String CATEGORY_PATH_DELIMITER = "/";

  private CategoryUtils() {}

  public static Category buildExpressCategory(String processName) {
    String categoryName = processName;
    if (processName.contains(CATEGORY_PATH_DELIMITER)) {
      String[] processNameArray = processName.split(CATEGORY_PATH_DELIMITER);
      categoryName = processNameArray[processNameArray.length - 1];
    }

    CategoryPath rootExpress = new CategoryPath("ExpressWorkflow");
    CategoryPath process = rootExpress.append(categoryName.trim());
    return Category.createFor(process,
        new LocalizedTextResolverFactory().createFor("Categories", Ivy.request().getProcessModelVersion()));
  }
}
