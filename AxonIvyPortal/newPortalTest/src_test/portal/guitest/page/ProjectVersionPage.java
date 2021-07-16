package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProjectVersionPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('project-version:engine-version-label')";
  }
  
  public boolean isEngineVersionDisplayed() {
    return isElementDisplayed(By.id("project-version:engine-version-label")); 
  }
  
  public boolean isPortalVersionDisplayed() {
    return isElementDisplayed(By.id("project-version:portal-version-label"));
  }
  
  public boolean isFirstProjectDisplayed() {
    return isElementDisplayed(By.id("project-version:application-list:0:application-project-version-table:0:project-name"));
  }
  
  public WebElement getProjectVersionDialog() {
    return findElementById("project-info-dialog");
  }
}
