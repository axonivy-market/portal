package portal.guitest.page;

import org.openqa.selenium.By;

public class ProcessesPage extends TemplatePage {

  private String processesWidgetId;

  public ProcessesPage() {
    this("process-widget");
  }

  public ProcessesPage(String processesWidgetId) {
    this.processesWidgetId = processesWidgetId;
  }

  public boolean hasCreateNewExpressWorkflowLink() {
    return isElementPresent(By.id("process-widget:axon-express-form:create-express-workflow"));
  }

  public String getProcessesWidgetId() {
    return processesWidgetId;
  }

  public void setProcessesWidgetId(String processesWidgetId) {
    this.processesWidgetId = processesWidgetId;
  }
}
