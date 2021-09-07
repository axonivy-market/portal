package portal.guitest.userexamples.page;

import org.openqa.selenium.By;

import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.TemplatePage;

public class UserExamplesEndPage extends TemplatePage {
  
  @Override
  protected String getLoadedLocator() {
    return "id('form:go-to-case-detail')";
  }
  
  public CaseDetailsPage goToCaseDetail() {
    click(By.id("form:go-to-case-detail"));
    return new CaseDetailsPage();
  }
}
